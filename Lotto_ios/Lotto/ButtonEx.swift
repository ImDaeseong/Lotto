
import UIKit

class ButtonEx : UIButton {
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        initButton()
    }
    
    required override init(frame: CGRect) {
        super.init(frame: frame)
        initButton()
    }
    
    private func initButton(){
        
        self.setTitle("등록", for: .normal)
        
        self.layer.borderColor = textFieldborderColor.cgColor
        self.layer.borderWidth = 1.0
        self.layer.cornerRadius = 5
        self.backgroundColor = UIColor.white
        
        if state == .normal {
           
            setTitleColor(grayColor, for: UIControl.State.normal)
            
        }else if state == .selected {
            
            setTitleColor(UIColor.lightText, for: UIControl.State.selected)
        }
    }
    
}
