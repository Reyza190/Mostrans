package com.example.mostrans.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mostrans.R
import com.example.mostrans.presentation.screen.list.ListCharactersScreen
import com.example.mostrans.presentation.screen.location.LocationCharacterScreen
import kotlinx.collections.immutable.persistentListOf

@Composable
fun NavigationBar(
    startScreen: NavigationBarScreen,
    onNavigateDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    val navigationBarScreens = remember {
        persistentListOf(
            NavigationBarScreen.ListCharacter,
            NavigationBarScreen.Location
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showNavigationBar = navigationBarScreens.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showNavigationBar) {
                androidx.compose.material3.NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    tonalElevation = 0.dp
                ) {
                    navigationBarScreens.forEach { screen ->
                        AddNavigationBarItem(
                            screen =screen,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }
                }
            }
        },
        content = { scaffoldPadding ->
            NavigationBarNavHost(
                navController = navController,
                startScreen = startScreen,
                onNavigateDetails = onNavigateDetail,
                modifier = Modifier.padding(scaffoldPadding)
            )
        },
        modifier = modifier
    )

}

@Composable
private fun NavigationBarNavHost(
    navController: NavHostController,
    startScreen: NavigationBarScreen,
    onNavigateDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        composable(route = NavigationBarScreen.ListCharacter.route) {
            ListCharactersScreen(onSelectedCharacter = { onNavigateDetails(it) })
        }

        composable(route = NavigationBarScreen.Location.route) {
            LocationCharacterScreen(onSelectedCharacter = { onNavigateDetails(it) })
        }
    }
}

@Composable
private fun RowScope.AddNavigationBarItem(
    screen: NavigationBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val selected = currentDestination?.hierarchy?.any { destination ->
        destination.route == screen.route
    } == true
    NavigationBarItem(selected = selected, onClick = {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }, icon = { /*TODO*/ }, label = {
        Text(
            text = stringResource(id = screen.nameResourceId), color = if (selected) colorResource(
                id = R.color.text_is_pressed
            ) else colorResource(id = R.color.not_text_is_pressed)
        )
    }, colors = NavigationBarItemDefaults.colors(
        selectedTextColor = MaterialTheme.colorScheme.onSurface,
        indicatorColor = MaterialTheme.colorScheme.background,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),)
}