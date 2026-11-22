package dev.yuyuyuyuyu.leafarium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.theme.LeafariumTheme
import dev.yuyuyuyuyu.leafarium.ui.editor.NewLeafDialog
import dev.yuyuyuyuyu.leafarium.ui.navigation.CompactBottomBar
import dev.yuyuyuyuyu.leafarium.ui.navigation.CompactTopBar
import dev.yuyuyuyuyu.leafarium.ui.navigation.Destination
import dev.yuyuyuyuyu.leafarium.ui.navigation.Navigator
import dev.yuyuyuyuyu.leafarium.ui.navigation.NewLeafButton
import dev.yuyuyuyuyu.leafarium.ui.navigation.Screen
import dev.yuyuyuyuyu.leafarium.ui.navigation.SideNavigation
import dev.yuyuyuyuyu.leafarium.ui.screen.Aside
import dev.yuyuyuyuyu.leafarium.ui.screen.ForestScreen
import dev.yuyuyuyuyu.leafarium.ui.screen.LeafScreen
import dev.yuyuyuyuyu.leafarium.ui.screen.PlaceholderScreen
import dev.yuyuyuyuyu.leafarium.ui.screen.TreeScreen

@Composable
@Preview
fun App() {
    LeafariumTheme {
        var backStack by remember { mutableStateOf(listOf<Screen>(Screen.Forest)) }
        var writingLeaf by remember { mutableStateOf(false) }
        val navigator =
            remember {
                Navigator(
                    openLeaf = { id -> backStack = backStack + Screen.LeafOf(id) },
                    openTree = { handle -> backStack = backStack + Screen.TreeOf(handle) },
                    open = { destination -> backStack = listOf(destination.screen) },
                    back = { backStack = backStack.dropLast(1).ifEmpty { listOf(Screen.Forest) } },
                    newLeaf = { writingLeaf = true },
                )
            }
        val screen = backStack.last()
        val destination = screen.destination ?: backStack.first().destination
        BoxWithConstraints(Modifier.fillMaxSize().background(LeafariumColors.Ground)) {
            if (maxWidth >= 720.dp) {
                WideLayout(screen, destination, navigator, showAside = maxWidth >= 1120.dp)
            } else {
                CompactLayout(screen, destination, navigator)
            }
        }
        if (writingLeaf) {
            NewLeafDialog(onDismiss = { writingLeaf = false })
        }
    }
}

private val Destination.screen: Screen
    get() =
        when (this) {
            Destination.Forest -> Screen.Forest
            Destination.Search -> Screen.Search
            Destination.Notifications -> Screen.Notifications
            Destination.MyTree -> Screen.TreeOf(SampleForest.me.handle)
            Destination.Settings -> Screen.Settings
        }

private val Screen.destination: Destination?
    get() =
        when (this) {
            Screen.Forest -> Destination.Forest
            Screen.Search -> Destination.Search
            Screen.Notifications -> Destination.Notifications
            Screen.Settings -> Destination.Settings
            is Screen.TreeOf -> if (handle == SampleForest.me.handle) Destination.MyTree else null
            is Screen.LeafOf -> null
        }

@Composable
private fun WideLayout(
    screen: Screen,
    destination: Destination?,
    navigator: Navigator,
    showAside: Boolean,
) {
    BoxWithConstraints(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Row(Modifier.widthIn(max = 1240.dp).fillMaxHeight()) {
            SideNavigation(destination, navigator, Modifier.width(260.dp).fillMaxHeight())
            VerticalDivider(color = LeafariumColors.Line)
            ScreenContent(
                screen = screen,
                navigator = navigator,
                compact = false,
                modifier =
                    Modifier
                        .weight(1f, fill = !showAside)
                        .widthIn(max = 640.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(LeafariumColors.Surface),
            )
            VerticalDivider(color = LeafariumColors.Line)
            if (showAside) {
                Aside(navigator, Modifier.width(340.dp).fillMaxHeight())
            }
        }
    }
}

@Composable
private fun CompactLayout(
    screen: Screen,
    destination: Destination?,
    navigator: Navigator,
) {
    val topLevel = screen.destination
    Scaffold(
        containerColor = LeafariumColors.Surface,
        topBar = {
            if (topLevel != null && topLevel != Destination.MyTree) {
                CompactTopBar(topLevel.label)
            }
        },
        bottomBar = { CompactBottomBar(destination, navigator) },
        floatingActionButton = { NewLeafButton(onClick = navigator.newLeaf) },
    ) { padding ->
        ScreenContent(screen, navigator, compact = true, modifier = Modifier.padding(padding).fillMaxSize())
    }
}

@Composable
private fun ScreenContent(
    screen: Screen,
    navigator: Navigator,
    compact: Boolean,
    modifier: Modifier = Modifier,
) {
    when (screen) {
        Screen.Forest -> ForestScreen(navigator, compact, modifier)
        Screen.Search -> PlaceholderScreen("Search", compact, modifier)
        Screen.Notifications -> PlaceholderScreen("Notifications", compact, modifier)
        Screen.Settings -> PlaceholderScreen("Settings", compact, modifier)
        is Screen.TreeOf -> TreeScreen(screen.handle, navigator, modifier)
        is Screen.LeafOf -> LeafScreen(screen.id, navigator, modifier)
    }
}
