package com.helsanf.modular.core.data.source.remote.respone


import com.google.gson.annotations.SerializedName

data class SportResponses(
    @SerializedName("teams")
    val teams: List<Team>
) {
    data class Team(
        @SerializedName("idAPIfootball")
        val idAPIfootball: String,
        @SerializedName("idLeague")
        val idLeague: String,
        @SerializedName("idSoccerXML")
        val idSoccerXML: String,
        @SerializedName("idTeam")
        val idTeam: String,
        @SerializedName("intStadiumCapacity")
        val intStadiumCapacity: String,
        @SerializedName("strAlternate")
        val strAlternate: String,
        @SerializedName("strCountry")
        val strCountry: String,
        @SerializedName("strDescriptionEN")
        val strDescriptionEN: String,
        @SerializedName("strDivision")
        val strDivision: Any,
        @SerializedName("strFacebook")
        val strFacebook: String,
        @SerializedName("strGender")
        val strGender: String,
        @SerializedName("strInstagram")
        val strInstagram: String,
        @SerializedName("strKeywords")
        val strKeywords: String,
        @SerializedName("strLeague")
        val strLeague: String,
        @SerializedName("strLocked")
        val strLocked: String,
        @SerializedName("strManager")
        val strManager: String,
        @SerializedName("strRSS")
        val strRSS: String,
        @SerializedName("strSport")
        val strSport: String,
        @SerializedName("strStadium")
        val strStadium: String,
        @SerializedName("strStadiumDescription")
        val strStadiumDescription: String,
        @SerializedName("strStadiumLocation")
        val strStadiumLocation: String,
        @SerializedName("strStadiumThumb")
        val strStadiumThumb: String,
        @SerializedName("strTeam")
        val strTeam: String,
        @SerializedName("strTeamBadge")
        val strTeamBadge: String,
        @SerializedName("strTeamBanner")
        val strTeamBanner: String,
        @SerializedName("strTeamJersey")
        val strTeamJersey: String,
        @SerializedName("strTeamLogo")
        val strTeamLogo: String,
        @SerializedName("strTeamShort")
        val strTeamShort: Any,
        @SerializedName("strTwitter")
        val strTwitter: String,
        @SerializedName("strWebsite")
        val strWebsite: String,
        @SerializedName("strYoutube")
        val strYoutube: String
    )
}