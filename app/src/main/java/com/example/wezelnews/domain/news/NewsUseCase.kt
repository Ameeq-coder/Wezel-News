package com.example.wezelnews.domain.news

data class NewsUseCase(
    val getNews: GetNews,
    val searchnews:SearchNewsCase,
    val inserarticle:InsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle
)
