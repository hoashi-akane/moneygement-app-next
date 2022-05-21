package com.example.moneygement.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.ShareLedgersQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShareRepository : GraphqlBase() {
    private val apolloClient = super.access()

    suspend fun getShareLedgerList(userId: Int): List<ShareLedgersQuery.ShareLedger>? {
        var ledgerIdList: List<ShareLedgersQuery.ShareLedger>? = null

        val job = CoroutineScope(Dispatchers.IO).launch {
            var response = try {
                apolloClient.query(ShareLedgersQuery(userId)).await()
            } catch (e: ApolloException) {
                e.printStackTrace()
                return@launch
            }

            var result = response.data?.ledger()
            if (result == null || response.hasErrors()) {
                return@launch
            } else {
                ledgerIdList = result.shareLedgers()
            }
        }
        job.join()
        return ledgerIdList
    }
}