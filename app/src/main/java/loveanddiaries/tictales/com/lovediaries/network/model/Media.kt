package loveanddiaries.tictales.com.lovediaries.network.model

data class Media(var picture: String, var cover: String?){
    override fun toString(): String {
        return "{picture: $picture, cover: $cover}"
    }
}