package com.xwindy.web.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xwindy.web.util.SysUtil;

/**
 * 新闻Bean
 * 对应数据库的news表
 * 
 * @author Dremy
 *
 */
public class News {

	private int id;
	
	private int publicId;
	
	private String publicName;
	
	private String publicIP;
	
	private String title;
	
	private String content;
	
	private String summary;
	
	private String datetime;
	
	private String url;
	
	private boolean push;
	
	private int clickNum;
	
	private int commentNum;
	
	private String firstPicUrl;
	
	

    public News() {};
	
	public News(int publicId, String publicIP, String title, String url, String content, String datetime, boolean push) {
	    
	    this.publicId = publicId;
	    this.publicIP = publicIP;
	    this.title = title;
	    this.content = content;
	    this.datetime = datetime;
	    this.url = url;
	    this.push = push;
	}
	
	@Override
    public String toString() {
        return "News [id=" + id + ", publicId=" + publicId + ", publicName=" + publicName + ", publicIP=" + publicIP
                + ", title=" + title + ", content=" + content + ", datetime=" + datetime + ", url=" + url + ", push="
                + push + ", clickNum=" + clickNum + ", commentNum=" + commentNum + "]";
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}
	
	public int getPublicId() {
		return publicId;
	}

	public void setPublicId(int publicId) {
		this.publicId = publicId;
	}

	public String getPublicIP() {
		return publicIP;
	}

	public void setPublicIP(String publicIP) {
		this.publicIP = publicIP;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		
		setSummary(content);
		setFirstPicUrl(content);
	}
	
	public void setSummary(String content) {
       content = SysUtil.removeHtmlTag(content);
        if(content.length() > 200) {
            content = content.substring(0, 200);
            content = content + "...";
        }
        this.summary = content;
	}
	
	public String getSummary() {
	    return summary;
	}

	
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String urlFrom) {
		this.url = urlFrom;
	}

	public boolean isPush() {
		return push;
	}

	public void setPush(Boolean push) {
			this.push = push;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	
	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

    public String getFirstPicUrl() {
        return firstPicUrl;
    }

    public void setFirstPicUrl(String content) {
        Pattern regex = Pattern.compile("<img .*?src=\"(.*?.jpg)\"");
        Matcher regexMatcher = regex.matcher(content);
        if (regexMatcher.find()) {
            this.firstPicUrl = regexMatcher.group(1);
        }
    }
	
	
}
