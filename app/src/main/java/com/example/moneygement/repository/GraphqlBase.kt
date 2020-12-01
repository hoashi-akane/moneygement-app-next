package com.example.moneygement.repository

import com.apollographql.apollo.ApolloClient
import com.example.moneygement.model.GRAPHQL_URI
import okhttp3.OkHttpClient

open class GraphqlBase {

    fun access(): ApolloClient {
        val okHttpClient = OkHttpClient()
        return ApolloClient.builder()
                .serverUrl(GRAPHQL_URI)
                .okHttpClient(okHttpClient)
                .build()
    }
}