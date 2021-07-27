package com.example.listadeafazeres

class AfazerModel (title: String?, body: String?, userName: String?) {
    private var title: String
    private var body: String
    private var userName: String
    init {
        this.title = title!!
        this.body = body!!
        this.userName = userName!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getBody(): String? {
        return body
    }
    fun setBody(userName: String?) {
        this.body = body!!
    }
    fun getUserName(): String? {
        return userName
    }
    fun setUserName(genre: String?) {
        this.userName = userName!!
    }
}