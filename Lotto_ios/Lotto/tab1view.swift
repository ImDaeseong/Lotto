
import UIKit

class tab1view: UIViewController {
    
    @IBOutlet weak var tableview1: UITableView!
    
    
    //총개수
    var nTotalcount : Int = 0
    
    var allLotto : [Lotto] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        initTableview()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        initData()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        allLotto.removeAll()
    }
    
    private func initTableview(){
        
        tableview1.delegate = self
        tableview1.dataSource = self
        
        tableview1.backgroundColor = .white
        tableview1.separatorStyle = .none
        
        let nibName = UINib(nibName: "item1Cell", bundle: nil)
        tableview1.register(nibName, forCellReuseIdentifier: "item1Cell")
    }
    
    private func initRefresh(){
        
        let RefreshControl = UIRefreshControl()
        RefreshControl.addTarget(self, action: #selector(updateRefresh(RefreshControl:)), for: .valueChanged)
        tableview1.addSubview(RefreshControl)
    }
    
    @objc func updateRefresh(RefreshControl : UIRefreshControl){
        
        RefreshControl.endRefreshing()
        tableview1.reloadData()
    }
    
    private func initData(){
        
        //전체 총개수
        nTotalcount = DbHelper.shared.getLottoRowCount()
        
        allLotto = DbHelper.shared.getLotto()
        
        //변경된 내용 반영
        //tableview1.reloadData()
        
        //당겨서 새로 고침
        initRefresh()
    }
}

extension tab1view : swipecellDelegate {
    
    func didswipeLeft(cell : UITableViewCell){
        
        let indexPath = self.tableview1.indexPath(for: cell)
        
        if let index = indexPath?.row {
            print("didswipeLeft:\(index)")
        }
    }
    
    func didswipeRight(cell : UITableViewCell){
        
        let indexPath = self.tableview1.indexPath(for: cell)
        
        if let index = indexPath?.row {
            print("didswipeRight:\(index)")
        }
    }
}

extension tab1view : UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableview1.dequeueReusableCell(withIdentifier: "item1Cell", for: indexPath) as! item1Cell
        
        //cell 클릭 효과 무시
        cell.selectionStyle = .none
        cell.delegate = self
        
        let rIndex = allLotto[indexPath.row].rIndex
        let sDate = allLotto[indexPath.row].Date
        let num1 = allLotto[indexPath.row].Part1
        let num2 = allLotto[indexPath.row].Part2
        let num3 = allLotto[indexPath.row].Part3
        let num4 = allLotto[indexPath.row].Part4
        let num5 = allLotto[indexPath.row].Part5
        let num6 = allLotto[indexPath.row].Part6
        
        //label 선택 글자 색상 변경
        let labelvalue = "\(rIndex) 회 당첨결과(\(sDate))"
        let attributeStr : NSMutableAttributedString = NSMutableAttributedString(string: labelvalue)
        attributeStr.addAttribute(.foregroundColor, value: orangeColor, range: (labelvalue as NSString).range(of: "\(rIndex)"))
        cell.label1.attributedText = attributeStr
        
        cell.num1.txtText = num1
        cell.num1.bgColor = getLottoColor(number: Int(num1)!)
        
        cell.num2.txtText = num2
        cell.num2.bgColor = getLottoColor(number: Int(num2)!)
        
        cell.num3.txtText = num3
        cell.num3.bgColor = getLottoColor(number: Int(num3)!)
        
        cell.num4.txtText = num4
        cell.num4.bgColor = getLottoColor(number: Int(num4)!)
        
        cell.num5.txtText = num5
        cell.num5.bgColor = getLottoColor(number: Int(num5)!)
        
        cell.num6.txtText = num6
        cell.num6.bgColor = getLottoColor(number: Int(num6)!)
        
        return cell
    }
    
    //전체 데이터 개수
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return nTotalcount
    }
    
    //섹션수
    func numberOfSections(in tableView: UITableView) -> Int {
        
        return 1
    }
    
    //상단 공간
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        
        return 5.0
    }
    
    //하단 공간
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        
        return 5.0
    }
    
    //선택셀
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        tableview1.deselectRow(at: indexPath, animated: true)
    }
}
