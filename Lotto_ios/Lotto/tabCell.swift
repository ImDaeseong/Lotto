import UIKit

class tabCell: UICollectionViewCell {
    
    @IBOutlet weak var namelabel: UILabel!
    
    @IBOutlet weak var lineview: UIView!
    
    override var isSelected: Bool {
        didSet {
            
            self.namelabel.textColor = isSelected ? .white : grayColor
            self.lineview.backgroundColor = isSelected ? orangeColor : .clear
            
        }
    }
}
