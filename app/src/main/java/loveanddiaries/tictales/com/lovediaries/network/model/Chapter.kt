package loveanddiaries.tictales.com.lovediaries.network.model

import com.google.gson.annotations.SerializedName

data class Chapter(
        var id: Int? = null,

        @SerializedName("chapter_parent_id")
        var chapterParentId: String? = null,

        @SerializedName("chapter_group_id")
        var chapterGroupId: Int? = null,

        var title: String? = null,

        var slug: String? = null,

        var description: String? = null,

        var media: String? = null,

        var data: Any? = null,

        var ordering: Int? = null,

        var state: Int? = null,

        @SerializedName("free_until_at")
        var freeUntilAt: String? = null,

        @SerializedName("opened_at")
        var openedAt: String? = null,

        @SerializedName("closed_at")
        var closedAt: String? = null,

        @SerializedName("created_at")
        var createdAt: String? = null,

        @SerializedName("updated_at")
        var updatedAt: String? = null,

        var prices: Prices? = null,

        @SerializedName("can_continue")
        var canContinue: Boolean? = null,

        @SerializedName("can_restart")
        var canRestart: Boolean? = null,

        var completed: Boolean? = null,

        var failed: Boolean? = null,

        @SerializedName("show_history")
        var showHistory: Boolean? = null,

        @SerializedName("show_options")
        var showOptions: Boolean? = null,

        var locked: Locked? = null
){
    data class Prices(var restart: Int? = null, var default: Int? = null){
        override fun toString(): String {
            return "{restart: $restart, default: $default}"
        }
    }

    data class Locked(var price: Boolean? = null, var parent: Boolean? = null){
        override fun toString(): String {
            return "{price: $price, parent: $parent}"
        }
    }

    override fun toString(): String {
        return "{id: $id, chapterParentId: $chapterParentId, chapterGroupId: $chapterGroupId, title: $title}"
    }
}