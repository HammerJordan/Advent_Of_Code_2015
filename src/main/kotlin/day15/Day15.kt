package day15

import Day

fun main() = Day15().run()
class Day15 : Day(15) {

    data class Ingredient(
        val name: String,
        val capacity: Int,
        val durability: Int,
        val flavor: Int,
        val texture: Int,
        val calories: Int
    )

    fun parseIngredients(data: String): List<Ingredient> {
        val regex =
            "(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)"
                .toRegex(RegexOption.MULTILINE)

        return regex
            .findAll(data)
            .map {
                Ingredient(
                    it.groupValues[1],
                    it.groupValues[2].toInt(),
                    it.groupValues[3].toInt(),
                    it.groupValues[4].toInt(),
                    it.groupValues[5].toInt(),
                    it.groupValues[6].toInt()
                )
            }.toList()
    }

    private fun scoreRecipe(ingredients: Map<Ingredient, Int>, useCalories: Boolean): Int {
        var capacity = 0
        var durability = 0
        var flavor = 0
        var texture = 0
        var calories = 0

        for ((ingredient, count) in ingredients) {
            capacity += ingredient.capacity * count
            durability += ingredient.durability * count
            flavor += ingredient.flavor * count
            texture += ingredient.texture * count
            calories += ingredient.calories * count
        }
        if (listOf(capacity, durability, flavor, texture).min() <= 0)
            return 0

        if (useCalories && calories != 500)
            return 0

        return capacity * durability * flavor * texture
    }

    private fun findBest(ingredients: List<Ingredient>, useCalories: Boolean): Int {
        val bucketSize = 100
        val maxSize = 100 - ingredients.size + 1

        val current = ingredients.associateWith { 1 }.toMutableMap()
        current[ingredients.last()] = bucketSize - ingredients.count() + 1

        val results = mutableListOf<Int>()

        while (true) {
            val total = scoreRecipe(current, useCalories)
            results.add(total)

            if (current[ingredients.first()]!! + 1 >= maxSize) {
                break
            } else {
                do {
                    for (i in ingredients.reversed()) {
                        var currentC = current[i]!!
                        currentC++
                        if (currentC > maxSize) {
                            currentC = 1
                            current[i] = currentC
                        } else {
                            current[i] = currentC
                            break
                        }
                    }
                } while (current.values.sum() != 100)
            }
        }

        return results.max()
    }


    override fun partOne(data: String): String {

        val ingredients = parseIngredients(data)
        return findBest(ingredients, false).toString()
    }


    override fun partTwo(data: String): String {
        val ingredients = parseIngredients(data)
        return findBest(ingredients, true).toString()
    }

}