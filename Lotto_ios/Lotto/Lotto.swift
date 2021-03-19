import Foundation

struct Lotto {
    
    var rIndex : Int = 0
    var Date : String = ""
    var Part1 : String = ""
    var Part2 : String = ""
    var Part3 : String = ""
    var Part4 : String = ""
    var Part5 : String = ""
    var Part6 : String = ""
    var Bonus : String = ""
    
    init(rIndex : Int, Date : String, Part1 : String, Part2 : String, Part3 : String, Part4 : String, Part5 : String, Part6 : String, Bonus : String) {
        
        self.rIndex = rIndex
        self.Date = Date
        self.Part1 = Part1
        self.Part2 = Part2
        self.Part3 = Part3
        self.Part4 = Part4
        self.Part5 = Part5
        self.Part6 = Part6
        self.Bonus = Bonus
    }
}
