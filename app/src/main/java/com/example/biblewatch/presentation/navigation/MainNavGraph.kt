package com.example.biblewatch.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.biblewatch.presentation.screens.home.HomeScreen
import com.example.biblewatch.presentation.screens.stories.birth.BirthScreen
import com.example.biblewatch.presentation.screens.stories.genesis.GenesisScreen
import com.example.biblewatch.presentation.screens.study.AnimalsGameScreen
import com.example.biblewatch.presentation.screens.study.DrawScreen2
import com.example.biblewatch.presentation.screens.study.StudyScreen
import com.example.biblewatch.ui.theme.backgroundColorNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    val showBottomBar = AppDestination.Companion.bottomBarItems.any { dest ->
        currentRoute?.let { route ->
            dest.route.startsWith(route)
        } ?: false
    }
    NavigationSuiteScaffold(
        layoutType = if (showBottomBar) NavigationSuiteType.NavigationBar
        else NavigationSuiteType.None, navigationSuiteItems = {
            if (showBottomBar) {
                AppDestination.Companion.bottomBarItems.forEach { destination ->
                    item(icon = {
                        Box(modifier = Modifier.size(40.dp)) {
                            Icon(
                                painter = painterResource(destination.imageRes),
                                contentDescription = destination.label,
                                tint = if (destination.route.startsWith(currentRoute ?: "\n")) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                },
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(25.dp)
                            )
                            if (destination is AppDestination.Home) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .background(
                                            color = Color.Red, shape = RoundedCornerShape(8.dp)
                                        )
                                        .size(width = 24.dp, height = 12.dp)
                                ) {
                                    BasicText(
                                        text = "NEW",
                                        maxLines = 1,
                                        style = TextStyle.Default.copy(color = Color.White),
                                        autoSize = TextAutoSize.StepBased(minFontSize = 1.sp),
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .padding(horizontal = 2.dp)
                                    )
                                }
                            }
                        }

                    }, label = {
                        Text(
                            text = destination.label,
                            color = if (destination.route.startsWith(currentRoute ?: "\n")) {
                                // Selected text - more prominent
                                MaterialTheme.colorScheme.primary
                            } else {
                                // Unselected text - less prominent
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            fontSize = if (destination.route.startsWith(currentRoute ?: "\n")) {
                                12.sp // Slightly larger for selected
                            } else {
                                11.sp
                            }
                        )
                    }, selected = false, onClick = {
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
                }
            }
        }, navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = backgroundColorNavBar,
            // These colors affect the overall bar, not individual items
            navigationBarContentColor = Color.Transparent
        )
    ) {
        // ... rest of your code
        Scaffold(modifier = Modifier.Companion.fillMaxSize()) { _ ->
            NavHost(
                navController = navController,
                startDestination = AppDestination.Home.route,
            ) {
                homeGraph(navController)
                studyGraph(navController)
            }
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(startDestination = "home", route = AppDestination.Home.route) {
        composable("home") {
            HomeScreen(
                navigateToStoryBirth = { navController.navigatePreserveState(Birth.route) },
                navigateToStoryGenesis = { navController.navigatePreserveState(Genesis.route) },
            )
        }
        composable(Birth.route, deepLinks = listOf(navDeepLink {
            uriPattern = "biblewatch://story/birth"
        })) {
            BirthScreen {
                navController.popBackStack()
            }
        }
        composable(Genesis.route, deepLinks = listOf(navDeepLink {
            uriPattern = "biblewatch://story/genesis"
        })) {
            GenesisScreen {
                navController.popBackStack()
            }
        }
    }
}

fun NavGraphBuilder.studyGraph(navController: NavController) {
    navigation(startDestination = "study", route = AppDestination.Study.route) {
        composable("study") {
            StudyScreen(onBack = {
                navController.popBackStack()
            }, navigateToDraw = {
                navController.navigatePreserveState(Draw.route)
            }, navigateToAnimals = {
                navController.navigatePreserveState(AnimalsGame.route)
            })
        }
        composable(Draw.route) {
            DrawScreen2(onBack = {
                navController.popBackStack()
            })
        }
        composable(AnimalsGame.route) {
            AnimalsGameScreen(onBack = {
                navController.popBackStack()
            })
        }
    }
}

fun NavController.navigatePreserveState(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
private fun TestPreview() {
    MainNavGraph()
}
