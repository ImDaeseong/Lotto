package com.daeseong.lottoplayer.Database

class Lotto {

    private var rIndex : Int = 0
    private var date : String = ""
    private var part1 : Int = 0
    private var part2 : Int = 0
    private var part3 : Int = 0
    private var part4 : Int = 0
    private var part5 : Int = 0
    private var part6 : Int = 0
    private var bonus : Int = 0

    constructor() {}

    constructor(rIndex: Int, Date: String, Part1: Int, Part2: Int, Part3: Int, Part4: Int, Part5: Int, Part6: Int, Bonus: Int ) {
        this.rIndex = rIndex
        this.date = Date
        this.part1 = Part1
        this.part2 = Part2
        this.part3 = Part3
        this.part4 = Part4
        this.part5 = Part5
        this.part6 = Part6
        this.bonus = Bonus
    }

    fun getrIndex(): Int {
        return this.rIndex
    }

    fun getDate() : String {
        return this.date
    }

    fun getPart1(): Int {
        return this.part1
    }

    fun getPart2(): Int {
        return this.part2
    }

    fun getPart3(): Int {
        return this.part3
    }

    fun getPart4(): Int {
        return this.part4
    }

    fun getPart5(): Int {
        return this.part5
    }

    fun getPart6(): Int {
        return this.part6
    }

    fun getBonus(): Int {
        return this.bonus
    }
}