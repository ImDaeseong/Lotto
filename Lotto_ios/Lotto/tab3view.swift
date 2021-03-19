
import UIKit

class tab3view: UIViewController {
    
    @IBOutlet weak var scrollview: UIScrollView!
    
    @IBOutlet weak var textfieldTime: TextFieldEx!
    @IBOutlet weak var textfieldDate: TextFieldEx!
    @IBOutlet weak var textfieldNum1: TextFieldEx!
    @IBOutlet weak var textfieldNum2: TextFieldEx!
    @IBOutlet weak var textfieldNum3: TextFieldEx!
    @IBOutlet weak var textfieldNum4: TextFieldEx!
    @IBOutlet weak var textfieldNum5: TextFieldEx!
    @IBOutlet weak var textfieldNum6: TextFieldEx!
    @IBOutlet weak var textfieldBonus: TextFieldEx!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //키보드 타입
        textfieldTime.keyboardType = .numberPad
        textfieldDate.keyboardType = .default
        textfieldNum1.keyboardType = .numberPad
        textfieldNum2.keyboardType = .numberPad
        textfieldNum3.keyboardType = .numberPad
        textfieldNum4.keyboardType = .numberPad
        textfieldNum5.keyboardType = .numberPad
        textfieldNum6.keyboardType = .numberPad
        textfieldBonus.keyboardType = .numberPad
        
        textfieldTime.delegate = self
        textfieldTime.tag = 0
        
        textfieldDate.delegate = self
        textfieldDate.tag = 1
        
        textfieldNum1.delegate = self
        textfieldNum1.tag = 2
        
        textfieldNum2.delegate = self
        textfieldNum2.tag = 3
        
        textfieldNum3.delegate = self
        textfieldNum3.tag = 4
        
        textfieldNum4.delegate = self
        textfieldNum4.tag = 5
        
        textfieldNum5.delegate = self
        textfieldNum5.tag = 6
        
        textfieldNum6.delegate = self
        textfieldNum6.tag = 7
        
        textfieldBonus.delegate = self
        textfieldBonus.tag = 8
        textfieldBonus.returnKeyType = .done
        
        //키보드 보임/숨김 여부 감시
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillAppear(_:)), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillDisappear(_:)), name: UIResponder.keyboardWillHideNotification, object: nil)
      
        
        //키보드 보임
        textfieldTime.becomeFirstResponder()
    }
    
    @objc func keyboardWillAppear(_ sender : NotificationCenter){
        
        //print("keyboard 보임")
        
        UIView.animate(withDuration: 0.7, animations: {
            self.view.layoutIfNeeded()
        })
                
        let tapGestureRecognizer : UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(tab3view.DismissKeyboard))
        view.addGestureRecognizer(tapGestureRecognizer)
    }

    @objc func keyboardWillDisappear(_ sender : NotificationCenter){
        
        //print("keyboard 숨김")
        
        UIView.animate(withDuration: 1, animations: {
            self.view.layoutIfNeeded()
        })
               
        if let recognizers = self.view.gestureRecognizers {
                   
            for recognizer in recognizers {
                self.view.removeGestureRecognizer(recognizer)
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        //키보드 보임/숨김 여부 감시 종료
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    @IBAction func btnSet_click(_ sender: UIButton) {
        
        guard let textfieldTime = self.textfieldTime.text else { return }
        guard let textfieldDate = self.textfieldDate.text else { return }
        guard let textfieldNum1 = self.textfieldNum1.text else { return }
        guard let textfieldNum2 = self.textfieldNum2.text else { return }
        guard let textfieldNum3 = self.textfieldNum3.text else { return }
        guard let textfieldNum4 = self.textfieldNum4.text else { return }
        guard let textfieldNum5 = self.textfieldNum5.text else { return }
        guard let textfieldNum6 = self.textfieldNum6.text else { return }
        guard let textfieldBonus = self.textfieldBonus.text else { return }
        
        if ( textfieldTime.isEmpty || textfieldDate.isEmpty || textfieldNum1.isEmpty || textfieldNum2.isEmpty || textfieldNum3.isEmpty || textfieldNum4.isEmpty || textfieldNum5.isEmpty || textfieldNum6.isEmpty || textfieldBonus.isEmpty ) {
            return
        }
        
        DbHelper.shared.insertLotto(rIndex: Int(textfieldTime)!, Date: textfieldDate, Part1: textfieldNum1, Part2: textfieldNum2, Part3: textfieldNum3, Part4: textfieldNum4, Part5: textfieldNum5, Part6: textfieldNum6, Bonus: textfieldBonus)
        
        //업데이트 여부 알림 필요
        self.textfieldTime.text = ""
        self.textfieldDate.text = ""
        self.textfieldNum1.text = ""
        self.textfieldNum2.text = ""
        self.textfieldNum3.text = ""
        self.textfieldNum4.text = ""
        self.textfieldNum5.text = ""
        self.textfieldNum6.text = ""
        self.textfieldBonus.text = ""
    }
    
    @objc func DismissKeyboard(){
        
        textfieldTime.resignFirstResponder()
        textfieldDate.resignFirstResponder()
        textfieldNum1.resignFirstResponder()
        textfieldNum2.resignFirstResponder()
        textfieldNum3.resignFirstResponder()
        textfieldNum4.resignFirstResponder()
        textfieldNum5.resignFirstResponder()
        textfieldNum6.resignFirstResponder()
        textfieldBonus.resignFirstResponder()
    }
}


extension tab3view : UITextFieldDelegate {
    
    func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        
        //print("텍스트 입력 상태를 시작해도 되는지 물음")
        
        if textField.isEqual(textfieldTime){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 10)
        } else if textField.isEqual(textfieldDate){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 40)
        } else if textField.isEqual(textfieldNum1){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 90)
        } else if textField.isEqual(textfieldNum2){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 120)
        } else if textField.isEqual(textfieldNum3){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 150)
        } else if textField.isEqual(textfieldNum4){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 220)
        } else if textField.isEqual(textfieldNum5){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 240)
        } else if textField.isEqual(textfieldNum6){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 260)
        } else if textField.isEqual(textfieldBonus){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y + 280)
        }
        
        
        return true
    }
    
    func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
        
        //print("텍스트 입력 상태를 종료해도 되는지 물음")
        
        if textField.isEqual(textfieldTime){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 10)
        } else if textField.isEqual(textfieldDate){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 40)
        } else if textField.isEqual(textfieldNum1){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 90)
        } else if textField.isEqual(textfieldNum2){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 120)
        } else if textField.isEqual(textfieldNum3){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 150)
        } else if textField.isEqual(textfieldNum4){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 220)
        } else if textField.isEqual(textfieldNum5){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 240)
        } else if textField.isEqual(textfieldNum6){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 260)
        } else if textField.isEqual(textfieldBonus){
            
            scrollview.contentOffset = CGPoint(x: scrollview.contentOffset.x, y: scrollview.contentOffset.y - 280)
        }
        
        return true
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        
        //print("textfield 입력시작")
        
        //입력시 배경색 흰색으로 변경
        textField.backgroundColor = UIColor.white
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        
        //print("textfield 입력종료")
        
        //입력 종료시 원래색으로 변경
        textField.backgroundColor = textFieldColor
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        
        //print("텍스트 입력값이 변경 될때 호출 \(string)")
        
        return true
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        
        //print("textfield 리턴키 클릭시\(textField.tag)")
        
        /*
        switch textField {
        case textfieldTime:
            textfieldDate.becomeFirstResponder()
            break
            
        case textfieldDate:
            textfieldNum1.becomeFirstResponder()
            break
            
        case textfieldNum1:
            textfieldNum2.becomeFirstResponder()
            break
            
        case textfieldNum2:
            textfieldNum3.becomeFirstResponder()
            break
            
        case textfieldNum3:
            textfieldNum4.becomeFirstResponder()
            break
            
        case textfieldNum4:
            textfieldNum5.becomeFirstResponder()
            break
            
        case textfieldNum5:
            textfieldNum6.becomeFirstResponder()
            break
            
        case textfieldNum6:
            textfieldBonus.becomeFirstResponder()
            break
            
        case textfieldBonus:
            textfieldBonus.becomeFirstResponder()
            break
            
        default:
            break
        }
        */
        
        return true
    }
}
