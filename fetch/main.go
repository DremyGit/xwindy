package main

// The program is used to fetch news from HFUT_XC website
// and store into the database.
import (
	"database/sql"
	"errors"
	"flag"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
	"io/ioutil"
	"log"
	"net/http"
	"regexp"
	"time"
)

const (
	BASE_URL  = "http://xc.hfut.edu.cn"
	LIST_NEWS = "/120/list%d.htm"
	LIST_INFO = "/121/list%d.htm"
	LIST_REPT = "/xsdt/list%d.htm"
)

type Article struct {
	title   string
	content string
	date    string
	url     string
}

func main() {

	tatalPagePtf := flag.Int("page", 1, "Number of page list")
	useArticleTime := flag.Bool("t", false, "Use article date as datetime")
	dbUser := flag.String("u", "root", "Data base username")
	dbPass := flag.String("p", "123456", "Data base password")
	dbName := flag.String("db", "cip", "Database db name")
	flag.Parse()

	conn := new(Connect)
	if err := conn.connectDB(dbUser, dbPass, dbName); err != nil {
		log.Fatal("Connect Error")
	}

	count := 0
	for aType, listUrl := range []string{LIST_NEWS, LIST_INFO, LIST_REPT} {
		for i := *tatalPagePtf; i > 0; i-- {
			urlList := getUrlLisyByType(listUrl, i)
			for index, _ := range urlList {
				article, err := getArticleFromUrl(urlList[len(urlList)-1-index])
				if err != nil {
					fmt.Println(err)
					continue
				} else {
					exist, err := conn.isArticleExisted(&article)
					if err != nil {
						fmt.Println(err)
						continue
					}
					if exist {
						continue
					}

					if err := conn.addToSchoolnews(&article); err != nil {
						fmt.Println(err)
						continue
					}

					if *useArticleTime {
						article.date = article.date + " 12:00:00"
					} else {
						article.date = time.Now().Format("2006-01-02 15:04:05")
					}
					if err := conn.addToNews(&article, aType); err != nil {
						fmt.Println(err)
						continue
					}
					count++
					fmt.Printf("New: title: %s\tdate: %s\turl:%s\n", article.title, article.date, article.url)
				}
			}
		}
	}
	fmt.Printf("Count: %d\n", count)
	conn.close()
}

func fetch(url string) (string, error) {
	res, err := http.Get(BASE_URL + url)
	if err != nil {
		log.Fatal(err)
		return "", err
	}
	htmlByte, err := ioutil.ReadAll(res.Body)
	if err != nil {
		log.Fatal(err)
		return "", err
	}
	html := string(htmlByte)
	return html, nil
}

func getUrlLisyByType(newsType string, page int) (urlList []string) {

	listUrl := fmt.Sprintf(newsType, page)
	html, err := fetch(listUrl)
	if err != nil {
		log.Fatal(err)
		return []string{}
	}

	re := regexp.MustCompile(`<a class=" articlelist1_a_title ".*?href="(/[^"]*?)"`)
	result := re.FindAllStringSubmatch(html, -1)
	for _, value := range result {
		urlList = append(urlList, value[1])
	}
	return urlList
}

func getArticleFromUrl(newsUrl string) (article Article, err error) {
	html, err := fetch(newsUrl)
	if err != nil {
		return article, err
	}
	resTitle := regexp.MustCompile(`<h1 class="atitle">([^<]*?)</h1>`).FindStringSubmatch(html)
	resDate := regexp.MustCompile(`<span class="posttime">发布时间:([^<]*?)</span>`).FindStringSubmatch(html)
	resContent := regexp.MustCompile(`<div class="entry" id="infobox">\s*((?:.*?\s*?)*?)</div>`).FindStringSubmatch(html)

	if len(resTitle) == 0 {
		fmt.Println(resTitle)
		return article, errors.New(newsUrl + ": Regex title error")
	}
	if len(resDate) == 0 {
		return article, errors.New(newsUrl + ": Regex date error")
	}
	if len(resContent) == 0 {
		return article, errors.New(newsUrl + ": Regex content error")
	}

	article.title = cleanBlank(resTitle[1])
	article.date = resDate[1]
	article.content = cleanHtmlStyle(resContent[1])
	article.url = BASE_URL + newsUrl
	return article, nil
}

func cleanBlank(str string) string {
	return regexp.MustCompile(`\s+`).ReplaceAllString(str, "")
}

func cleanHtmlStyle(html string) (res string) {
	res = html
	res = regexp.MustCompile(`<(\w+?)([^>]*?text-align:right;)`).ReplaceAllString(res, "<$1 TAR $2")
	res = regexp.MustCompile(` (?:style|id|class)="[^"]*"`).ReplaceAllString(res, "")
	res = regexp.MustCompile(`TAR`).ReplaceAllString(res, `style="text-align:right;"`)
	res = regexp.MustCompile(`(href|src)="(/[^"]*?)"`).ReplaceAllString(res, `$1="`+BASE_URL+`$2"`)
	return res
}

type Connect struct {
	db *sql.DB
}

func (c *Connect) connectDB(dbUser, dbPass, dbName *string) error {
	var err error
	dbString := fmt.Sprintf("%s:%s@/%s", *dbUser, *dbPass, *dbName)
	c.db, err = sql.Open("mysql", dbString)
	if err != nil {
		return err
	}
	err = c.db.Ping()
	if err != nil {
		return err
	}
	return nil
}

func (c *Connect) isArticleExisted(article *Article) (bool, error) {
	stmtExist, err := c.db.Prepare("SELECT count(0) FROM cip_schoolnews WHERE title LIKE ? AND date = ?")
	if err != nil {
		return false, err
	}
	defer stmtExist.Close()
	exist := 0
	stmtExist.QueryRow(article.title+"%", article.date).Scan(&exist)
	return exist != 0, nil
}

func (c *Connect) addToSchoolnews(article *Article) error {
	stmtInst, err := c.db.Prepare("INSERT INTO cip_schoolnews (type, title, content, date, url, gettime) VALUES (?,?,?,?,?,?)")
	if err != nil {
		return err
	}
	defer stmtInst.Close()
	_, err = stmtInst.Exec("test", article.title, article.content, article.date, article.url, time.Now().Unix())
	if err != nil {
		return err
	}
	return nil
}

func (c *Connect) addToNews(article *Article, aType int) error {
	stmtInst2, err := c.db.Prepare("INSERT INTO news (public_id, public_ip, title, content, datetime, url_from, push) VALUES (?, ?, ?, ?, ?, ?, 0)")
	if err != nil {
		return err
	}
	defer stmtInst2.Close()
	publicId := []int{5, 7, 8}
	_, err = stmtInst2.Exec(publicId[aType], "127.0.0.1", article.title, article.content, article.date, article.url)
	if err != nil {
		return err
	}
	return nil
}

func (c *Connect) close() {
	c.db.Close()
}
