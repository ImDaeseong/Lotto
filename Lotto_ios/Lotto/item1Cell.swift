
import UIKit

protocol swipecellDelegate {
    func didswipeLeft(cell : UITableViewCell)
    func didswipeRight(cell : UITableViewCell)
}

class item1Cell: UITableViewCell {

    var delegate : swipecellDelegate?

    @IBOutlet weak var label1: UILabel!
    
    @IBOutlet weak var num1: roundview!
    
    @IBOutlet weak var num2: roundview!
    
    @IBOutlet weak var num3: roundview!
    
    @IBOutlet weak var num4: roundview!
    
    @IBOutlet weak var num5: roundview!
    
    @IBOutlet weak var num6: roundview!
    
    var swipeLeft : UISwipeGestureRecognizer!
    var swipeRight : UISwipeGestureRecognizer!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        initSwipe()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }
    
    private func initSwipe(){
        
        swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(item1Cell.swipeGesture(_:)))
        swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(item1Cell.swipeGesture(_:)))
        self.addGestureRecognizer(swipeLeft)
        self.addGestureRecognizer(swipeRight)
    }
    
    @objc func swipeGesture(_ gesture: UIGestureRecognizer) {
        
        if let swipe = gesture as? UISwipeGestureRecognizer {
            switch swipe.direction {
            case UISwipeGestureRecognizer.Direction.left:
                delegate?.didswipeLeft(cell: self)
                break
            case UISwipeGestureRecognizer.Direction.right:
                delegate?.didswipeRight(cell: self)
                break
            default:
                break
            }
        }
    }
    
}
