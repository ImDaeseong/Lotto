
import UIKit

class roundview : UIView {
    
    @IBInspectable var bgColor : UIColor = UIColor.clear {
        didSet {
            self.backgroundColor = bgColor
        }
    }
    
    @IBInspectable var txtColor : UIColor = UIColor.clear {
        didSet {
            self.txtlabel.textColor = txtColor
        }
    }
    
    @IBInspectable var txtSize : CGFloat = 0 {
        didSet {
            self.txtlabel.font = UIFont.systemFont(ofSize: txtSize)
        }
    }
    
    @IBInspectable var txtText : String = "" {
        didSet {
            self.txtlabel.text = txtText
        }
    }
    
    let txtlabel : UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textAlignment = .center
        label.font = UIFont.systemFont(ofSize: 12)
        return label
    }()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        self.layer.cornerRadius = self.frame.height / 2
        
        self.addSubview(txtlabel)
        
        txtlabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 5).isActive = true
        txtlabel.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -5).isActive = true
        txtlabel.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
    }
}

