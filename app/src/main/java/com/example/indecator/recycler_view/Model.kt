package com.example.viewfrontback.recycler_view


object MockModel{

    val image1 =
        "https://cdn-beta.dojoin.com/upload/permanent/thumbnail/e765239b-4fc6-48f9-9396-165dbb6c01e1.jpg"
    val image2 =
        "https://cdn-beta.dojoin.com/upload/permanent/thumbnail/5361ca78-badc-4aca-94f8-5047d91b01b7.jpg"
    val image3 =
        "https://cdn-beta.dojoin.com/upload/permanent/thumbnail/3a4a0073-35b6-43c7-b08d-0a528257d480.jpg"

    val image4 = "https://cdn.dojoin.com/upload/permanent/category-icons/9639329a-e550-4ce1-8457-d9c428037c6e.jpg"


    val DataList = listOf(
        Model("Hello To Main Screen", image1),
        Model("Have Great Summer", image2),
        Model("Getting weird", image3),
        Model("Run for you Live", image4),
        Model("Run for you Live", image4),
        Model("Hello To Main Screen", image1),
        Model("Have Great Summer", image2),
        Model("Getting weird", image3),
    )

}


data class Model(
    val title:String,
    val imageUrl:String
)
