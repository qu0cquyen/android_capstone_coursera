package com.example.littlelemon

import java.util.Objects

interface Destinations{
    val arguments: List<Objects>
    val destination: String
}

object NavigationDirections{
    val OnBoarding = object : Destinations{
        override val arguments = emptyList<Objects>()
        override val destination = "Onboarding"
    }

    val Home = object : Destinations{
        override val arguments = emptyList<Objects>()
        override val destination = "Home"
    }

    val Profile = object : Destinations{
        override val arguments = emptyList<Objects>()
        override val destination = "Profile"
    }
}