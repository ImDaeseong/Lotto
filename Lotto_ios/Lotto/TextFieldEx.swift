import UIKit

class TextFieldEx: UITextField {
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        initTextField()
    }
    
    required override init(frame: CGRect) {
        super.init(frame: frame)
        initTextField()
    }
    
    private func initTextField(){
        
        self.font = UIFont.systemFont(ofSize: 14)
        self.textColor = UIColor.black
        self.textAlignment = .left
        
        //placeholder 텍스트 색상
        self.attributedPlaceholder = NSAttributedString(string: self.placeholder != nil ? self.placeholder! : "", attributes: [NSAttributedString.Key.foregroundColor : grayColor])
        
        self.borderStyle = .roundedRect
        self.backgroundColor = textFieldColor
        self.tintColor = orangeColor //포커스 색상
        
        self.layer.cornerRadius = 5
        self.layer.borderColor = textFieldborderColor.cgColor
        self.layer.borderWidth = 1.0
    }
}
