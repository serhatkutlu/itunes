package com.msk.itunes.Responce.Data.SearcResponce.track


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("artistId")
    val artistId: Int?=null, // 909253
    @SerializedName("artistName")
    val artistName: String?=null, // Jack Johnson
    @SerializedName("artistViewUrl")
    val artistViewUrl: String?=null, // https://music.apple.com/us/artist/jack-johnson/909253?uo=4
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?=null, // https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/ae/4c/d4/ae4cd42a-80a9-d950-16f5-36f01a9e1881/source/100x100bb.jpg
    @SerializedName("artworkUrl30")
    val artworkUrl30: String?=null, // https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/ae/4c/d4/ae4cd42a-80a9-d950-16f5-36f01a9e1881/source/30x30bb.jpg
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?=null, // https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/ae/4c/d4/ae4cd42a-80a9-d950-16f5-36f01a9e1881/source/60x60bb.jpg
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int?=null, // 345346702
    @SerializedName("collectionArtistName")
    val collectionArtistName: String?=null, // Jack Johnson
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String?=null, // https://itunes.apple.com/us/artist/sony-pictures-entertainment/345346702?uo=4
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String?=null, // Jack Johnson and Friends: Sing-A-Longs and Lullabies for the Film Curious George
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String?=null, // notExplicit
    @SerializedName("collectionHdPrice")
    val collectionHdPrice: Double?=null, // 9.99
    @SerializedName("collectionId")
    val collectionId: Int?=null, // 1469577723
    @SerializedName("collectionName")
    val collectionName: String?=null, // Jack Johnson and Friends: Sing-A-Longs and Lullabies for the Film Curious George
    @SerializedName("collectionPrice")
    val collectionPrice: Double?=null, // 9.99
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String?=null, // https://music.apple.com/us/album/upside-down/1469577723?i=1469577741&uo=4
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String?=null, // PG-13
    @SerializedName("country")
    val country: String?=null, // USA
    @SerializedName("currency")
    val currency: String?=null, // USD
    @SerializedName("discCount")
    val discCount: Int?=null, // 1
    @SerializedName("discNumber")
    val discNumber: Int?=null, // 1
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean?=null, // true
    @SerializedName("isStreamable")
    val isStreamable: Boolean?=null, // true
    @SerializedName("kind")
    val kind: String?=null, // song
    @SerializedName("longDescription")
    val longDescription: String?=null, // Four teenagers in detention discover an old video console with a game they’ve never heard of. When they decide to play, they are immediately sucked into the jungle world of Jumanji in the bodies of their avatars (Dwayne Johnson, Jack Black, Kevin Hart, and Karen Gillan). They’ll have to complete the adventure of their lives filled with fun, thrills and danger or be stuck in the game forever!
    @SerializedName("previewUrl")
    val previewUrl: String?=null, // https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/5e/5b/3d/5e5b3df4-deb5-da78-5d64-fe51d8404d5c/mzaf_13341178261601361485.plus.aac.p.m4a
    @SerializedName("primaryGenreName")
    val primaryGenreName: String?=null, // Rock
    @SerializedName("releaseDate")
    val releaseDate: String?=null, // 2005-01-01T12:00:00Z
    @SerializedName("shortDescription")
    val shortDescription: String?=null, // Four teenagers in detention discover an old video console with a game they’ve never heard of. When
    @SerializedName("trackCensoredName")
    val trackCensoredName: String?=null, // Upside Down
    @SerializedName("trackCount")
    val trackCount: Int?=null, // 14
    @SerializedName("trackExplicitness")
    val trackExplicitness: String?=null, // notExplicit
    @SerializedName("trackHdPrice")
    val trackHdPrice: Double?=null, // 9.99
    @SerializedName("trackHdRentalPrice")
    val trackHdRentalPrice: Double?=null, // 3.99
    @SerializedName("trackId")
    val trackId: Int?=null, // 1469577741
    @SerializedName("trackName")
    val trackName: String?=null, // Upside Down
    @SerializedName("trackNumber")
    val trackNumber: Int?=null, // 1
    @SerializedName("trackPrice")
    val trackPrice: Double?=null, // 1.29
    @SerializedName("trackRentalPrice")
    val trackRentalPrice: Double?=null, // 3.99
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int?=null, // 208643
    @SerializedName("trackViewUrl")
    val trackViewUrl: String?=null, // https://music.apple.com/us/album/upside-down/1469577723?i=1469577741&uo=4
    @SerializedName("wrapperType")
    val wrapperType: String?=null,// track,    @SerializedName("advisories")
    @SerializedName("advisories")
    val advisories: List<String>?=null,
    @SerializedName("appletvScreenshotUrls")
    val appletvScreenshotUrls: List<Any>?=null,
    @SerializedName("artworkUrl512")
    val artworkUrl512: String?=null, // https://is5-ssl.mzstatic.com/image/thumb/Purple122/v4/95/23/6a/95236a27-d9b7-1dbd-f379-5eaede4b0c97/source/512x512bb.jpg
    @SerializedName("averageUserRating")
    val averageUserRating: Double?=null, // 4.7096900000000001540456651127897202968597412109375
    @SerializedName("averageUserRatingForCurrentVersion")
    val averageUserRatingForCurrentVersion: Double?=null, // 4.7096900000000001540456651127897202968597412109375
    @SerializedName("bundleId")
    val bundleId: String?=null, // com.midasplayer.apps.candycrushsaga
    @SerializedName("currentVersionReleaseDate")
    val currentVersionReleaseDate: String?=null, // 2022-04-19T12:11:33Z
    @SerializedName("description")
    val description: String?=null, // Start playing Candy Crush Saga today – a legendary puzzle game loved by millions of players around the world.With over a trillion levels played, this sweet match 3 puzzle game is one of the most popular mobile games of all time!Switch and match Candies in this tasty puzzle adventure to progress to the next level for that sweet winning feeling! Solve puzzles with quick thinking and smart moves, and be rewarded with delicious rainbow-colored cascades and tasty candy combos!Plan your moves by matching 3 or more candies in a row, using boosters wisely in order to overcome those extra sticky puzzles! Blast the chocolate and collect sweet candy across thousands of levels, guaranteed to have you craving more!Candy Crush Saga features:THE GAME THAT KEEPS YOU CRAVING MOREThousands of the best levels and puzzles in the Candy Kingdom and with more added every 2 weeks your sugar fix is never far away! MANY WAYS TO WIN REWARDSCheck back daily and spin the Daily Booster Wheel to receive free tasty rewards, and take part in time limited challenges to earn boosters to help you level up!  VARIETY OF SUGAR-COATED CHALLENGESSweet ways to play: Game modes including Target Score, Clear the Jelly, Collect the Ingredients and Order ModePLAY ALONE OR WITH FRIENDSGet to the top of the leaderboard events and compare scores with friends and competitors!Levels range from easy to hard for all adults to enjoy – accessible on-the-go, offline and online.It's easy to sync the game between devices and unlock full game features when connected to the Internet or Wifi.Follow us to get news and updates; facebook.com/CandyCrushSaga, Twitter @CandyCrushSaga, Youtube https://www.youtube.com/user/CandyCrushOfficialVisit https://community.king.com/en/candy-crush-saga to access the Community and competitions!Candy Crush Saga is completely free to play but some optional in-game items will require payment.You can turn off the payment feature by disabling in-app purchases in your device’s settings.By downloading this game you are agreeing to our terms of service; http://about.king.com/consumer-terms/termsDo not sell my data: King shares your personal information with advertising partners to personalize ads. Learn more at https://king.com/privacyPolicy.  If you wish to exercise your Do Not Sell My Data rights, you can do so by contacting us via the in game help centre or by going to https://soporto.king.com/Have fun playing Candy Crush Saga the sweetest match 3 puzzle game around! If you enjoy playing Candy Crush Saga, you may also enjoy its sister puzzle games; Candy Crush Soda Saga, Candy Crush Jelly Saga and Candy Crush Friends Saga!
    @SerializedName("features")
    val features: List<String>?=null,
    @SerializedName("fileSizeBytes")
    val fileSizeBytes: String?=null, // 367181824
    @SerializedName("formattedPrice")
    val formattedPrice: String?=null, // Free
    @SerializedName("genreIds")
    val genreIds: List<String>?=null,
    @SerializedName("genres")
    val genres: List<String>?=null,
    @SerializedName("ipadScreenshotUrls")
    val ipadScreenshotUrls: List<String>?=null,
    @SerializedName("isGameCenterEnabled")
    val isGameCenterEnabled: Boolean?=null, // false
    @SerializedName("isVppDeviceBasedLicensingEnabled")
    val isVppDeviceBasedLicensingEnabled: Boolean?=null, // true
    @SerializedName("languageCodesISO2A")
    val languageCodesISO2A: List<String>?=null,
    @SerializedName("minimumOsVersion")
    val minimumOsVersion: String?=null, // 9
    @SerializedName("price")
    val price: Double, // 0.00
    @SerializedName("primaryGenreId")
    val primaryGenreId: Int?=null, // 6014
    @SerializedName("releaseNotes")
    val releaseNotes: String?=null, // We hope you’re having fun playing Candy Crush Saga! We update the game every week so don't forget to download the latest version to get all the sweet new features and levels!New to the game? Don’t be shy, join the fun!
    @SerializedName("screenshotUrls")
    val screenshotUrls: List<String>?=null,
    @SerializedName("sellerName")
    val sellerName: String?=null, // King.com Limited
    @SerializedName("sellerUrl")
    val sellerUrl: String?=null, // http://candycrushsaga.com/
    @SerializedName("supportedDevices")
    val supportedDevices: List<String>?=null,
    @SerializedName("trackContentRating")
    val trackContentRating: String?=null, // 4+
    @SerializedName("userRatingCount")
    val userRatingCount: Int?=null, // 2417870
    @SerializedName("userRatingCountForCurrentVersion")
    val userRatingCountForCurrentVersion: Int?=null, // 2417870
    @SerializedName("version")
    val version: String?=null, // 1.225.0.1
    @SerializedName("amgArtistId")
    val amgArtistId: Int?=null, // 162028
    @SerializedName("copyright")
    val copyright: String ?=null// © 2009 Hachette Audio


)