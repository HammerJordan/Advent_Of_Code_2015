package day21

import Day

fun main() = Day21().run()
class Day21 : Day(21) {

    sealed class Item(open val cost: Int, open val damage: Int, open val armor: Int) {
        data object None : Item(0, 0, 0)

        data class Weapon(override val cost: Int, override val damage: Int) : Item(cost, damage, 0)
        data class Armor(override val cost: Int, override val armor: Int) : Item(cost, 0, armor)
        data class Ring(override val cost: Int, override val damage: Int, override val armor: Int) :
            Item(cost, damage, armor)
    }

    class Shop {


        val weapons = listOf(
            Item.Weapon(8, 4),
            Item.Weapon(10, 5),
            Item.Weapon(25, 6),
            Item.Weapon(40, 7),
            Item.Weapon(74, 8),
        ).sortedBy { it.cost }

        val armors = listOf(
            Item.None,
            Item.Armor(13, 1),
            Item.Armor(31, 2),
            Item.Armor(53, 3),
            Item.Armor(75, 4),
            Item.Armor(102, 5),
        ).sortedBy { it.cost }

        val rings = listOf(
            Item.None,
            Item.None,
            Item.Ring(25, 1, 0),
            Item.Ring(50, 2, 0),
            Item.Ring(100, 3, 0),
            Item.Ring(20, 0, 1),
            Item.Ring(40, 0, 2),
            Item.Ring(80, 0, 3),
        ).sortedBy { it.cost }


        fun getCombos(): Sequence<List<Item>> {
            return sequence {
                for (weapon in weapons.indices) {
                    for (armor in armors.indices) {
                        for (leftRing in rings.indices) {
                            for (rightRing in rings.indices) {
                                if (rightRing == leftRing)
                                    continue
                                yield(listOf(weapons[weapon], armors[armor], rings[leftRing], rings[rightRing]))
                            }
                        }
                    }

                }
            }


        }
    }

    open class Attacker(val startHitPoints: Int, val startDamage: Int, val startArmor: Int) {
        var hitPoints = startHitPoints
        val isDead get() = hitPoints <= 0
        open val damage get() = startDamage
        open val armor get() = startArmor

        fun reset() {
            hitPoints = startHitPoints
        }


        fun takeDamage(dmg: Int) {
            val effectiveDamage = (dmg - armor).coerceAtLeast(1)

            hitPoints -= effectiveDamage
        }


        companion object {
            fun fromString(input: String): Attacker {
                val data = input
                    .lines()
                    .map { """(\d+)""".toRegex().find(it)!!.value.toInt() }
                return Attacker(
                    startHitPoints = data[0],
                    startDamage = data[1],
                    startArmor = data[2]

                )
            }
        }
    }

    open class Player(startHitPoints: Int, startDamage: Int, startArmor: Int) : Attacker(
        startHitPoints, startDamage,
        startArmor
    ) {
        private val equippedItems = mutableListOf<Item>()


        override val damage get() = startDamage + equippedItems.sumOf { it.damage }
        override val armor get() = startDamage + equippedItems.sumOf { it.armor }

        fun setItems(items: List<Item>) {
            equippedItems.clear()
            equippedItems.addAll(items)
        }

        companion object {
            fun default(): Player = Player(100, 0, 0)
        }
    }

    fun simulateCombatRound(player: Attacker, boss: Attacker): Boolean {
        val playerDamage = player.damage
        boss.takeDamage(playerDamage)
        if (boss.isDead)
            return true

        val bossDamage = boss.damage
        player.takeDamage(bossDamage)

        return player.isDead
    }

    fun canPlayerBeatBoss(player: Attacker, boss: Attacker): Boolean {


        while (true) {
            if (simulateCombatRound(player, boss))
                return boss.isDead
        }

    }


    override fun partOne(data: String): String {
        val winWith = mutableListOf<List<Item>>()
        val player = Player.default()
        val boss = Attacker.fromString(data)
        val shop = Shop()

        for (combo in shop.getCombos()) {
            player.reset()
            boss.reset()
            player.setItems(combo)
            if (canPlayerBeatBoss(player, boss))
                winWith.add(combo)
        }


        return winWith.minBy { it.sumOf { it.cost } }.sumOf { it.cost }.toString()

    }


    override fun partTwo(data: String): String {
        val lossWith = mutableListOf<List<Item>>()
        val player = Player.default()
        val boss = Attacker.fromString(data)
        val shop = Shop()

        for (combo in shop.getCombos()) {
            player.reset()
            boss.reset()
            player.setItems(combo)
            if (!canPlayerBeatBoss(player, boss))
                lossWith.add(combo)
        }

        return lossWith.maxBy { items -> items.sumOf { it.cost } }.sumOf { it.cost }.toString()
    }

}