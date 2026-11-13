package dev.yuyuyuyuyu.leafarium.sample

import dev.yuyuyuyuyu.leafarium.theme.AvatarTone

data class Tree(
    val handle: String,
    val name: String,
    val initials: String,
    val tone: AvatarTone,
    val bio: String,
    val plantedIn: String,
    val leafCount: Int,
    val followingCount: Int,
    val followerCount: Int,
)

data class Leaf(
    val id: String,
    val tree: Tree,
    val grownAgo: String,
    val grownAt: String,
    val body: String,
    val replyCount: Int,
    val shareCount: Int,
    val likeCount: Int,
    val liked: Boolean = false,
    val shared: Boolean = false,
)

object SampleForest {
    val me =
        Tree(
            handle = "yu",
            name = "yu",
            initials = "YU",
            tone = AvatarTone.Moss,
            bio = "Growing **Leafarium**.",
            plantedIn = "November 2026",
            leafCount = 0,
            followingCount = 3,
            followerCount = 0,
        )

    private val mina =
        Tree(
            handle = "mina",
            name = "Mina Aoki",
            initials = "MA",
            tone = AvatarTone.Bark,
            bio = "Gardener on weekends, designer on weekdays. Writing a **garden journal** one Leaf at a time.",
            plantedIn = "March 2026",
            leafCount = 128,
            followingCount = 42,
            followerCount = 310,
        )

    private val kai =
        Tree(
            handle = "kai",
            name = "Kai Morimoto",
            initials = "KM",
            tone = AvatarTone.Sky,
            bio = "Kotlin, compilers, and slow mornings.",
            plantedIn = "March 2026",
            leafCount = 412,
            followingCount = 88,
            followerCount = 1204,
        )

    private val sora =
        Tree(
            handle = "sora",
            name = "Sora Ito",
            initials = "SI",
            tone = AvatarTone.Moss,
            bio = "Reading in the shade.",
            plantedIn = "April 2026",
            leafCount = 57,
            followingCount = 19,
            followerCount = 96,
        )

    private val ren =
        Tree(
            handle = "ren",
            name = "Ren",
            initials = "RN",
            tone = AvatarTone.Bark,
            bio = "Small notes on writing.",
            plantedIn = "June 2026",
            leafCount = 23,
            followingCount = 12,
            followerCount = 40,
        )

    val suggestedTrees =
        listOf(
            Tree(
                handle = "hana",
                name = "Hana Takeda",
                initials = "HT",
                tone = AvatarTone.Sky,
                bio = "",
                plantedIn = "May 2026",
                leafCount = 31,
                followingCount = 20,
                followerCount = 77,
            ),
            Tree(
                handle = "oakclub",
                name = "Oak Club",
                initials = "OK",
                tone = AvatarTone.Moss,
                bio = "",
                plantedIn = "May 2026",
                leafCount = 9,
                followingCount = 2,
                followerCount = 140,
            ),
        )

    val forest =
        listOf(
            Leaf(
                id = "mina-1",
                tree = mina,
                grownAgo = "4m",
                grownAt = "11:02 AM · Nov 13, 2026",
                body =
                    """
                    Moved my garden journal over to Leafarium. Writing in **Markdown** feels like home.

                    - Basil: *thriving*
                    - Tomatoes: still green
                    - Mint: taking over everything
                    """.trimIndent(),
                replyCount = 3,
                shareCount = 2,
                likeCount = 18,
                liked = true,
            ),
            Leaf(
                id = "kai-1",
                tree = kai,
                grownAgo = "32m",
                grownAt = "10:24 AM · Nov 13, 2026",
                body =
                    """
                    Small Kotlin trick I use every day:

                    ```kotlin
                    val fresh = tree.leaves
                        .filter { it.isFresh }
                        .sortedByDescending { it.grownAt }
                    ```

                    Reads like a sentence. Chaining `filter` and `sortedByDescending` keeps the intent **on the surface**.
                    """.trimIndent(),
                replyCount = 12,
                shareCount = 9,
                likeCount = 64,
            ),
            Leaf(
                id = "sora-1",
                tree = sora,
                grownAgo = "1h",
                grownAt = "9:51 AM · Nov 13, 2026",
                body =
                    """
                    ## Weekend reading list

                    1. *The Hidden Life of Trees*
                    2. A field guide to ferns
                    3. Anything with a map in the front

                    > Slow reading is a kind of growing.
                    """.trimIndent(),
                replyCount = 5,
                shareCount = 14,
                likeCount = 41,
                shared = true,
            ),
            Leaf(
                id = "ren-1",
                tree = ren,
                grownAgo = "3h",
                grownAt = "7:40 AM · Nov 13, 2026",
                body = "Reminder: wrap a word in backticks and it becomes `code`. Wrap it in asterisks and it becomes *gentle*.",
                replyCount = 0,
                shareCount = 1,
                likeCount = 7,
            ),
            Leaf(
                id = "mina-2",
                tree = mina,
                grownAgo = "2d",
                grownAt = "4:15 PM · Nov 11, 2026",
                body =
                    """
                    ## Seed swap this Saturday

                    Bring what you have, take what you need. Details on the [community board](https://example.com).
                    """.trimIndent(),
                replyCount = 8,
                shareCount = 21,
                likeCount = 52,
            ),
        )

    val replies =
        mapOf(
            "kai-1" to
                listOf(
                    Leaf(
                        id = "mina-r1",
                        tree = mina,
                        grownAgo = "18m",
                        grownAt = "10:38 AM · Nov 13, 2026",
                        body = "Stealing this. Does `asSequence()` help when the Tree gets big?",
                        replyCount = 1,
                        shareCount = 0,
                        likeCount = 6,
                    ),
                    Leaf(
                        id = "kai-r1",
                        tree = kai,
                        grownAgo = "12m",
                        grownAt = "10:44 AM · Nov 13, 2026",
                        body =
                            """
                            It does once you chain more than two steps:

                            - no intermediate lists
                            - stops early with `take(n)`
                            """.trimIndent(),
                        replyCount = 0,
                        shareCount = 2,
                        likeCount = 11,
                        liked = true,
                    ),
                ),
        )

    val trees = (listOf(me, mina, kai, sora, ren) + suggestedTrees).associateBy { it.handle }

    val homeForest = forest.filter { it.id != "mina-2" }

    fun leaf(id: String): Leaf? = (forest + replies.values.flatten()).firstOrNull { it.id == id }

    fun leavesOf(handle: String): List<Leaf> = forest.filter { it.tree.handle == handle }
}
