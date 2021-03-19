
import UIKit

class LabelEx: UILabel {
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        initLabel()
    }
    
    required override init(frame: CGRect) {
        super.init(frame: frame)
        initLabel()
    }
    
    private func initLabel(){
        
        self.font = UIFont.boldSystemFont(ofSize: 15)
        self.textColor = UIColor.black
        self.textAlignment = .left
       
        self.backgroundColor = UIColor.clear
    }
}
