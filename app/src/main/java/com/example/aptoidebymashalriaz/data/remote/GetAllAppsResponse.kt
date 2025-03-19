package com.example.aptoidebymashalriaz.data.remote

import com.google.gson.annotations.SerializedName

data class GetAllAppsResponse(
    @SerializedName("listApps")
    val appsList: AppsListResponse?
)

data class AppsListResponse(
    @SerializedName("info")
    val info: InfoResponse?,
    @SerializedName("datasets")
    val datasets: DatasetsResponse?
)

data class InfoResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("time")
    val time: TimeResponse?
)

data class DatasetsResponse(
    @SerializedName("all")
    val all: AllResponse?
)

data class TimeResponse(
    @SerializedName("seconds")
    val seconds: Double?,
    @SerializedName("human")
    val human: String?
)

data class AllResponse(
    @SerializedName("info")
    val info: InfoResponse?,
    @SerializedName("data")
    val data: DataResponse?
)

data class DataResponse(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("next")
    val next: Int?,
    @SerializedName("hidden")
    val hidden: Int?,
    @SerializedName("list")
    val list: List<AppResponse?>?
)

data class AppResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String?,
    @SerializedName("package")
    val packageName: String?,
    @SerializedName("store_id")
    val storeId: Int?,
    @SerializedName("store_name")
    val storeName: String?,
    @SerializedName("vername")
    val verName: String?,
    @SerializedName("vercode")
    val verCode: Int?,
    @SerializedName("md5sum")
    val md5sum: String?,
    @SerializedName("apk_tags")
    val apkTags: List<Any?>?,
    @SerializedName("size")
    val size: Long?,
    @SerializedName("downloads")
    val downloads: Int?,
    @SerializedName("pdownloads")
    val pDownloads: Int?,
    @SerializedName("added")
    val added: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("graphic")
    val graphic: String?,
    @SerializedName("uptype")
    val upType: String?
)