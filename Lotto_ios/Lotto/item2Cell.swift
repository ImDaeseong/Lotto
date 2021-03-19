
import UIKit

class item2Cell : UITableViewCell {
   
    @IBOutlet weak var numview: roundview!
    @IBOutlet weak var numtext: UILabel!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
        //print("override init")
        initCell()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        //print("required init")
        initCell()
    }
    
    override public func layoutSubviews() {
        super.layoutSubviews()
        
        //print("layoutSubviews")
    }
    
    private func initCell(){
        
        print("initCell")
    }
}

