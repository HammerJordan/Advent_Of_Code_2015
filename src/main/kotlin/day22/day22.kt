package day22

import Day
import kotlin.math.min

fun main() = Day22().run()
class Day22 : Day(22) {
    enum class Spell(val cost: Int) {
        MagicMissile(53),
        Drain(73),
        Shield(113),
        Poison(173),
        Recharge(229);

        companion object {
            val Spells = listOf(
                MagicMissile,
                Drain,
                Shield,
                Poison,
                Recharge,
            )
        }
    }


    data class Boss(var hp: Int, val dmg: Int) {
        val isDead get() = hp <= 0
    }

    data class Wizard(var hp: Int, var mp: Int, var armor: Int = 0) {
        val isDead get() = hp <= 0
    }

    data class Effects(
        var shield: Int = 0,
        var poison: Int = 0,
        var recharge: Int = 0,
        var hardEffect: Int = 0,
        var mpGained: Int = 0,
        var psnDmg: Int = 0
    )

    private infix fun Wizard.canCast(spell: Spell): Boolean = this.mp >= spell.cost
    private infix fun Boss.takeDamage(dmg: Int) {
        this.hp -= dmg
    }

    private infix fun Wizard.takeDamage(dmg: Int) {

        this.hp -= dmg - this.armor
    }

    private infix fun Spell.onCoolDown(effect: Effects): Boolean {
        return when (this) {
            Spell.MagicMissile, Spell.Drain -> false
            Spell.Shield -> effect.shield > 0
            Spell.Poison -> effect.poison > 0
            Spell.Recharge -> effect.recharge > 0
        }
    }


    private fun getMinToWin(p: Wizard, b: Boss, hardEffect: Int = 0): Int {
        val spellQue = ArrayDeque<List<Spell>>()

        for (spell in Spell.Spells) {
            val lst = listOf(spell)
            spellQue.addLast(lst)
        }

        var minMana = Int.MAX_VALUE

        while (spellQue.isNotEmpty()) {

            val spellList = spellQue.removeFirst()


            val effect = Effects(hardEffect = hardEffect)
            val player = p.copy()
            val boss = b.copy()

            var flag = false
            var manaCost = 0

            for (spell in spellList) {
                handleEffects(player, effect, boss)
                if (hardEffect > 0)
                    player.hp -= 1

                if (boss.isDead) {

                    minMana = min(minMana, manaCost)
                    println("Won: $minMana, ${spellQue.count()}")

                    flag = true
                    break
                }
                if (player.isDead) {
                    flag = true
                    break
                }
                if (!(player canCast spell)) {
                    flag = true
                    break
                }

                if (spell onCoolDown effect) {
                    flag = true
                    break
                }

                manaCost += spell.cost
                if (manaCost > minMana) {
                    flag = true
                    break
                }

                player.mp -= spell.cost

                when (spell) {
                    Spell.MagicMissile -> boss takeDamage 4
                    Spell.Drain -> {
                        boss takeDamage 2
                        player.hp += 2
                    }

                    Spell.Shield -> {
                        effect.shield = 6
                    }

                    Spell.Poison -> {
                        effect.poison = 6
                    }

                    Spell.Recharge -> {
                        effect.recharge = 5
                    }
                }

                handleEffects(player, effect, boss)
                if (boss.isDead) {

                    minMana = min(minMana, manaCost)
                    println("Won: $minMana, ${spellQue.count()}")
                    flag = true
                    break
                }

                player takeDamage boss.dmg

                if (player.isDead) {
                    flag = true
                    break
                }

            }
            if (!flag) {

                for (s in Spell.Spells) {
                    handleEffects(player, effect, boss)

                    if (s onCoolDown effect)
                        continue

                    if (!(player canCast s))
                        continue

                    val newSpellList = spellList.toMutableList()
                    newSpellList.add(s)
                    spellQue.addLast(newSpellList)
                }

            }
        }
        return minMana
    }


    private fun handleEffects(player: Wizard, effect: Effects, boss: Boss) {
        player.armor = 0
        if (effect.shield > 0) {
            effect.shield--
            player.armor = 7
        }
        if (effect.poison > 0) {
            effect.poison--
            effect.psnDmg += 3
            boss takeDamage 3
        }
        if (effect.recharge > 0) {
            effect.recharge--
            effect.mpGained += 101
            player.mp += 101
        }

    }


    override fun partOne(data: String): String {
        val player = Wizard(50, 500)
        val boss = Boss(71, 10)


        val min = getMinToWin(player, boss)

        return min.toString()
    }


    override fun partTwo(data: String): String {
        val player = Wizard(50, 500)
        val boss = Boss(71, 10)


        val min = getMinToWin(player, boss, hardEffect = 1)

        return min.toString()
    }

}


