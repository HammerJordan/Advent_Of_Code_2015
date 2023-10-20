import day21.Day21
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day21Test {

    val day = Day21()

    @Test
    fun simCombatRound() {
        val player = Day21.Character(8, 5, 5)
        val boss = Day21.Character(12, 7, 2)

        Assertions.assertFalse(day.simulateCombatRound(player, boss))

        Assertions.assertEquals(boss.hitPoints, 9)
        Assertions.assertEquals(player.hitPoints, 6)

        Assertions.assertFalse(day.simulateCombatRound(player, boss))

        Assertions.assertEquals(boss.hitPoints, 6)
        Assertions.assertEquals(player.hitPoints, 4)

        Assertions.assertFalse(day.simulateCombatRound(player, boss))


        Assertions.assertEquals(boss.hitPoints, 3)
        Assertions.assertEquals(player.hitPoints, 2)

        Assertions.assertTrue(day.simulateCombatRound(player, boss))

        Assertions.assertEquals(boss.hitPoints, 0)
        Assertions.assertEquals(player.hitPoints, 2)

    }
}