package com.example.buffbites.data

import com.example.buffbites.R
import com.example.buffbites.model.MenuItem
import com.example.buffbites.model.Restaurant

object Datasource {

    val restaurants = listOf(
        Restaurant(
            name = R.string.chickfila,
            menuItems = listOf(
                MenuItem(
                    name = "Chick-fil-A Deluxe Sandwich",
                    description = "A boneless breast of chicken seasoned to perfection, freshly breaded, pressure cooked in 100% refined peanut oil.",
                    price = 5.55
                ),
                MenuItem(
                    name = "Chick-fil-A Nuggets",
                    description = "Bite-sized pieces of boneless chicken breast, seasoned to perfection, and freshly breaded.",
                    price = 4.85
                ),
                MenuItem(
                    name = "Chick-fil-A Cool Wrap",
                    description ="Sliced grilled chicken breast nestled in lettuce with a blend of cheeses, rolled in a flour flat bread.",
                    price = 7.79
                ),
                MenuItem(
                    name = "Chick-fil-A Waffle Fries",
                    description = "Waffle-cut potatoes cooked in canola oil until crispy outside and tender inside. Sprinkled with Sea Salt.",
                    price = 2.35
                )
            )
        ),
        Restaurant(
            name = R.string.starbucks,
            menuItems = listOf(
                MenuItem(
                    name = "Pumpkin Spice Latte",
                    description = "Our signature espresso and steamed milk with the celebrated flavor combination of pumpkin, cinnamon, nutmeg and clove.",
                    price = 6.99
                ),
                MenuItem(
                    name = "Cappuccino",
                    description = "Dark, rich espresso lies in wait under a smoothed and stretched layer of thick milk foam. An alchemy of barista artistry and craft.",
                    price = 4.99
                ),
                MenuItem(
                    name = "Espresso",
                    description = "Our smooth signature Espresso Roast with rich flavor and caramelly sweetness is at the very heart of everything we do.",
                    price = 3.99
                ),
                MenuItem(
                    name = "Blueberry Muffin",
                    description = "Soft muffin with sweet, juicy blueberries and a hint of lemon, finished with a crunchy sugar topping.",
                    price = 4.29
                )
            )
        ),
        Restaurant(
            name = R.string.which_wich,
            menuItems = listOf(
                MenuItem(
                    name = "Cali Club",
                    description = "Turkey, Bacon, Avocado, Cheddar, Lettuce, Tomatoes, Red Onions & Mayo",
                    price = 8.99
                ),
                MenuItem(
                    name = "Buffalo Chicken",
                    description = "Chicken, Provolone, Buffalo Sauce, Lettuce, Tomatoes & Ranch",
                    price = 9.99
                ),
                MenuItem(
                    name = "Philly Cheesesteak",
                    description = "Sirloin Steak, Provolone, Caramelized Onions & Sautéed Bell Peppers",
                    price = 7.49,
                ),
                MenuItem(
                    name = "Club Wich",
                    description = "Turkey, Ham, Bacon, Pepper Jack, Lettuce, Tomatoes, Mayo & 1000 Island",
                    price = 10.99
                )
            )
        )
    )
}