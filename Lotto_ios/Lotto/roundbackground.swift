import UIKit

class roundbackground : UIView {
    
    @IBInspectable var cornerRadius : CGFloat = 0 {
        didSet {
            self.layer.cornerRadius = cornerRadius
        }
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        self.backgroundColor = textFieldborderColor
        self.layer.borderColor = UIColor.orange.cgColor
        self.layer.borderWidth = 0.0
        self.layer.cornerRadius = 3
    }
}
