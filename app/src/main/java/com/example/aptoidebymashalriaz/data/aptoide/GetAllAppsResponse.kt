package com.example.aptoidebymashalriaz.data.aptoide

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class GetAllAppsResponse(
    @SerializedName("listApps")
    val appsList: AppsList?
)

data class AppsList(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("datasets")
    val datasets: Datasets?
)

data class Info(
    @SerializedName("status")
    val status: String?,
    @SerializedName("time")
    val time: Time?
)

data class Datasets(
    @SerializedName("all")
    val all: All?
)

data class Time(
    @SerializedName("seconds")
    val seconds: Double?,
    @SerializedName("human")
    val human: String?
)

data class All(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("data")
    val data: Data?
)

data class Data(
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
    val list: List<App?>?
)

data class App(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("package")
    val packageX: String?,
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
    val size: BigInteger?,
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