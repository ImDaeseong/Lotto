
import UIKit

class tab2view: UIViewController {
    
    @IBOutlet weak var tableview1: UITableView!
   
    //총개수
    var nTotalcount : Int = 0
    
    //전체 가장 많은 수 60개 조회
    var TotalLottoTop : [LottoTop] = []
    
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
        
        TotalLottoTop.removeAll()
    }
    
    private func initTableview(){
        
        tableview1.delegate = self
        tableview1.dataSource = self
        
        tableview1.backgroundColor = .white
        tableview1.separatorStyle = .none
        //tableview1.separatorColor = .red
        //tableview1.layoutMargins = UIEdgeInsets.init(top: 5, left: 5, bottom: 5, right: 5)
        //tableview1.separatorInset = UIEdgeInsets.init(top: 5, left: 5, bottom: 5, right: 5)
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
        //nTotalcount = DbHelper.shared.getLottoRowCount()
        
        //전체 가장 많은 수 60개 조회
        var numTop : [LottoTop] = []
        numTop.append(contentsOf: DbHelper.shared.getLottoTop(nPart: 1))
        numTop.append(contentsOf: DbHelper.shared.getLottoTop(nPart: 2))
        numTop.append(contentsOf: DbHelper.shared.getLottoTop(nPart: 3))
        numTop.append(contentsOf: DbHelper.shared.getLottoTop(nPart: 4))
        numTop.append(contentsOf: DbHelper.shared.getLottoTop(nPart: 5))
        numTop.append(contentsOf: DbHelper.shared.getLottoTop(nPart: 6))
        
        //sorting
        for item in numTop.sorted(by: { $0.nCount > $1.nCount }) {
            let item = LottoTop(sPart: item.sPart, nCount: item.nCount)
            TotalLottoTop.append(item)
        }
        numTop.removeAll()
        
        //소팅된 데이터 총개수
        nTotalcount = TotalLottoTop.count
        
        //변경된 내용 반영
        //tableview1.reloadData()
        
        //당겨서 새로 고침
        initRefresh()
    }
}

extension tab2view : UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableview1.dequeueReusableCell(withIdentifier: "item2Cell", for: indexPath) as! item2Cell
        
        //cell 클릭 효과 무시
        cell.selectionStyle = .none
        
        let selectNum = TotalLottoTop[indexPath.row].sPart
        let selectCount = TotalLottoTop[indexPath.row].nCount
        
        cell.numview.txtText = selectNum
        cell.numview.bgColor = getLottoColor(number: Int(selectNum)!)
        
        
        //label 속성 변경
        let labelvalue = "\(selectCount) 회 당첨 되었습니다."
        let attributeStr : NSMutableAttributedString = NSMutableAttributedString(string: labelvalue)
        
        //선택 글자 색상 변경
        attributeStr.addAttribute(.foregroundColor, value: orangeColor, range: (labelvalue as NSString).range(of: "\(selectCount)"))
        
        //선택 글자 배경색 변경
        //attributeStr.addAttribute(.backgroundColor, value: UIColor.red, range: (labelvalue as NSString).range(of: "\(selectCount)"))
        
        //선택 글자 밑줄 긋기
        attributeStr.addAttribute(.underlineColor, value: UIColor.red, range: (labelvalue as NSString).range(of: "\(selectCount)"))
        attributeStr.addAttribute(.underlineStyle, value: 1, range: (labelvalue as NSString).range(of: "\(selectCount)"))
        cell.numtext.attributedText = attributeStr
        
        //label 기본
        //cell.numtext.text = "\(selectCount) 회 당첨 되었습니다."
        
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
