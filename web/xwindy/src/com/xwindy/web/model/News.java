package com.xwindy.web.model;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xwindy.web.util.SysUtil;

/**
 * 资讯类
 * @author Dremy
 *
 */
public class News implements Serializable{

	/**
     * 序列化id
     */
    private static final long serialVersionUID = 2371167105428688287L;

    /**
     * 资讯id
     */
    private int id;

	/**
	 * 资讯评论列表
	 */
	private List<Comment> commentList;
	
	/**
	 * 资讯号id
	 */
	private int publicId;
	
    /**
     * 资讯号对象
     */
    private Publicer publicer;
	
    /**
     * 发布者ip
     */
	private String publicIP;
	
	/**
	 * 资讯标题
	 */
	private String title;
	
	/**
	 * 资讯正文
	 */
	private String content;
	
	/**
	 * 资讯简介
	 */
	private String summary;
	
	/**
	 * 发布时间
	 */
	private String datetime;
	
	/**
	 * 原文Url
	 */
	private String url;
	
	/**
	 * 推送状态: 0为不推送, 1为申请推送, 2为已推送, 3为拒绝推送
	 */
	private int push;
	
	/**
	 * 点击次数
	 */
	private int clickNum;
	
	/**
	 * 评论总数
	 */
	private int commentNum;
	
	/**
	 * 首图Url
	 */
	private String firstPicUrl;
	
	/**
	 * 资讯状态 0为隐藏, 1为显示
	 */
	private int state;

	/**
	 * 资讯类默认构造函数
	 */
    public News() {};
	
    /**
     * 资讯类构造函数
     */
	public News(int publicId, String publicIP, String title, String url, String content, String datetime, int push) {
	    
	    this.publicId = publicId;
	    this.publicIP = publicIP;
	    this.title = title;
	    this.content = content;
	    this.datetime = datetime;
	    this.url = url;
	    this.push = push;
	}
	
	/**
	 * 重写toString方法
	 */
	@Override
    public String toString() {
        return "News [id=" + id + ", publicId=" + publicId + ", publicIP=" + publicIP
                + ", title=" + title + ", content=" + content + ", datetime=" + datetime + ", url=" + url + ", push="
                + push + ", clickNum=" + clickNum + ", commentNum=" + commentNum + "]";
    }
	
	public int getId() {
		return id;
	}

    public void setId(int id) {
		this.id = id;
	}

    public Publicer getPublicer() {
        return publicer;
    }

    public void setPublicer(Publicer publicer) {
        this.publicer = publicer;
    }
    
	public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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

	public int getPush() {
        return push;
    }

    public void setPush(int push) {
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
	
	
}
