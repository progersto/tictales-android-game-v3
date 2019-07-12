package loveanddiaries.tictales.com.lovediaries.utils

const val BASE_URSL = "http://dev.tictales.com/api/fr/game/1/"
const val AUTH_URL = "auth/guest"
const val CHAPTERS_URL = "avatars/current/chapters"



//    get user login => POST : http://dev.tictales.com/api/fr/game/1/auth/basic
//
//    Body need : adId=xxx
//
//where adId = Google aaid => advertisment id of the current user
//
//then you will have a reponse with a json object
//
//where you will have a token
//
//this is use to call our api with authentification
//
//like to get list of chapters
//
//you can get list of current chapter using :
//
//http://dev.tictales.com/api/fr/game/1/avatars/current/chapters
//
//and you have to pass the token in header
//
//Authorization: Bearer <token>