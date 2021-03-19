import UIKit

public func getLottoColor(number : Int) -> UIColor {
    
    if (0 < number && 10 >= number) {
        
        let red : CGFloat = CGFloat(255) / 255.0
        let green : CGFloat = CGFloat(187) / 255.0
        let blue : CGFloat = CGFloat(51) / 255.0
        return UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        
    } else if (10 < number && 20 >= number) {
        
        let red : CGFloat = CGFloat(0) / 255.0
        let green : CGFloat = CGFloat(153) / 255.0
        let blue : CGFloat = CGFloat(204) / 255.0
        return UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        
    } else if (20 < number && 30 >= number) {
        
        let red : CGFloat = CGFloat(255) / 255.0
        let green : CGFloat = CGFloat(68) / 255.0
        let blue : CGFloat = CGFloat(68) / 255.0
        return UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        
    } else if (30 < number && 40 >= number) {
        
        let red : CGFloat = CGFloat(128) / 255.0
        let green : CGFloat = CGFloat(145) / 255.0
        let blue : CGFloat = CGFloat(158) / 255.0
        return UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        
    } else if (40 < number && 45 >= number) {
        
        let red : CGFloat = CGFloat(51) / 255.0
        let green : CGFloat = CGFloat(153) / 255.0
        let blue : CGFloat = CGFloat(51) / 255.0
        return UIColor(red: red, green: green, blue: blue, alpha: 1.0)
    }
    
    return UIColor(red: 255, green: 255, blue: 255, alpha: 1.0)
}


let orangeColor : UIColor = UIColor(red: CGFloat(255) / 255.0, green: CGFloat(153) / 255.0, blue: CGFloat(0) / 255.0, alpha: 1.0)

let grayColor : UIColor = UIColor(red: CGFloat(128) / 255.0, green: CGFloat(145) / 255.0, blue: CGFloat(158) / 255.0, alpha: 1.0)

let textFieldColor : UIColor = UIColor(red: CGFloat(248)/255.0, green: CGFloat(248)/255.0, blue: CGFloat(248)/255.0, alpha: 1.0)

let textFieldborderColor : UIColor = UIColor(red: CGFloat(237)/255.0, green: CGFloat(237)/255.0, blue: CGFloat(237)/255.0, alpha: 1.0)

let lineColor : UIColor = UIColor(red: CGFloat(148)/255.0, green: CGFloat(152)/255.0, blue: CGFloat(154)/255.0, alpha: 0.1)
