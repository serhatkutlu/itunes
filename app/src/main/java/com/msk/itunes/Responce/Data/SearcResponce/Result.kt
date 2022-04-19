package com.msk.itunes.Responce.Data.SearcResponce


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("artistId")
    val artistId: Int, // 909253
    @SerializedName("artistName")
    val artistName: String, // Jack Johnson
    @SerializedName("artistViewUrl")
    val artistViewUrl: String, // https://music.apple.com/us/artist/jack-johnson/909253?uo=4
    @SerializedName("artworkUrl100")
    val artworkUrl100: String, // https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/ae/4c/d4/ae4cd42a-80a9-d950-16f5-36f01a9e1881/source/100x100bb.jpg
    @SerializedName("artworkUrl30")
    val artworkUrl30: String, // https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/ae/4c/d4/ae4cd42a-80a9-d950-16f5-36f01a9e1881/source/30x30bb.jpg
    @SerializedName("artworkUrl60")
    val artworkUrl60: String, // https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/ae/4c/d4/ae4cd42a-80a9-d950-16f5-36f01a9e1881/source/60x60bb.jpg
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int, // 345346702
    @SerializedName("collectionArtistName")
    val collectionArtistName: String, // Jack Johnson
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String, // https://itunes.apple.com/us/artist/sony-pictures-entertainment/345346702?uo=4
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String, // Jack Johnson and Friends: Sing-A-Longs and Lullabies for the Film Curious George
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String, // notExplicit
    @SerializedName("collectionHdPrice")
    val collectionHdPrice: Double, // 9.99
    @SerializedName("collectionId")
    val collectionId: Int, // 1469577723
    @SerializedName("collectionName")
    val collectionName: String, // Jack Johnson and Friends: Sing-A-Longs and Lullabies for the Film Curious George
    @SerializedName("collectionPrice")
    val collectionPrice: Double, // 9.99
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String, // https://music.apple.com/us/album/upside-down/1469577723?i=1469577741&uo=4
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String, // PG-13
    @SerializedName("country")
    val country: String, // USA
    @SerializedName("currency")
    val currency: String, // USD
    @SerializedName("discCount")
    val discCount: Int, // 1
    @SerializedName("discNumber")
    val discNumber: Int, // 1
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean, // true
    @SerializedName("isStreamable")
    val isStreamable: Boolean, // true
    @SerializedName("kind")
    val kind: String, // song
    @SerializedName("longDescription")
    val longDescription: String, // Four teenagers in detention discover an old video console with a game they’ve never heard of. When they decide to play, they are immediately sucked into the jungle world of Jumanji in the bodies of their avatars (Dwayne Johnson, Jack Black, Kevin Hart, and Karen Gillan). They’ll have to complete the adventure of their lives filled with fun, thrills and danger or be stuck in the game forever!
    @SerializedName("previewUrl")
    val previewUrl: String, // https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/5e/5b/3d/5e5b3df4-deb5-da78-5d64-fe51d8404d5c/mzaf_13341178261601361485.plus.aac.p.m4a
    @SerializedName("primaryGenreName")
    val primaryGenreName: String, // Rock
    @SerializedName("releaseDate")
    val releaseDate: String, // 2005-01-01T12:00:00Z
    @SerializedName("shortDescription")
    val shortDescription: String, // Four teenagers in detention discover an old video console with a game they’ve never heard of. When
    @SerializedName("trackCensoredName")
    val trackCensoredName: String, // Upside Down
    @SerializedName("trackCount")
    val trackCount: Int, // 14
    @SerializedName("trackExplicitness")
    val trackExplicitness: String, // notExplicit
    @SerializedName("trackHdPrice")
    val trackHdPrice: Double, // 9.99
    @SerializedName("trackHdRentalPrice")
    val trackHdRentalPrice: Double, // 3.99
    @SerializedName("trackId")
    val trackId: Int, // 1469577741
    @SerializedName("trackName")
    val trackName: String, // Upside Down
    @SerializedName("trackNumber")
    val trackNumber: Int, // 1
    @SerializedName("trackPrice")
    val trackPrice: Double, // 1.29
    @SerializedName("trackRentalPrice")
    val trackRentalPrice: Double, // 3.99
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int, // 208643
    @SerializedName("trackViewUrl")
    val trackViewUrl: String, // https://music.apple.com/us/album/upside-down/1469577723?i=1469577741&uo=4
    @SerializedName("wrapperType")
    val wrapperType: String // track
)