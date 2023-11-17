
fun getCityInfo(city: String): CityInfo {
    return when (city) {
        "Yerevan" -> CityInfo(
            description = "Yerevan is the capital and largest city of Armenia, and one of the world's oldest continuously inhabited cities. Situated along the Hrazdan River, Yerevan is the administrative, cultural, and industrial center of the country.",
            imageUrl = "https://www.yerevan.am/uploads/media/page_gallery/0002/17/ebe33ce89f819fef4a1089d51c38b273feccd893.jpeg"
        )
        "Tehran" -> CityInfo(
            description = "Tehran is the capital of Iran, in the north of the country. Its central Golestan Palace complex, with its ornate rooms and marble throne, was the seat of power of the Qajar dynasty. The National Jewelry Museum holds many of the Qajar monarchs’ jewels, while the National Museum of Iran has artifacts dating back to Paleolithic times.",
            imageUrl = "https://www.nationsonline.org/gallery/Iran/Azadi-Monument-Tehran2.jpg"
        )
        else -> CityInfo("", "")
    }
}