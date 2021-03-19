
import UIKit
import WebKit

class tab4view: UIViewController {
    
    var webview : WKWebView!
    
    var ActivityIndicatorView : UIActivityIndicatorView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        initWKWebView()
        
        initActivityIndicatorView()
        
        initRefresh()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        initData()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
    }
    
    private func initWKWebView(){
        
        let preference = WKPreferences()
        preference.javaScriptCanOpenWindowsAutomatically = true //사용자가 동작하지 않아도 자바스크립트를 통해 새 창 열기 가능
        preference.javaScriptEnabled = true //자바스크립트 사용 가능
        
        
        let webConfig = WKWebViewConfiguration()
        webConfig.preferences = preference
        
        
        webview = WKWebView(frame: self.view.bounds, configuration: webConfig)
        self.view.addSubview(webview)
        
        webview.translatesAutoresizingMaskIntoConstraints = false
        webview.topAnchor.constraint(equalTo: view.topAnchor, constant: 8).isActive = true
        webview.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -8).isActive = true
        webview.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 8).isActive = true
        webview.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -8).isActive = true
        
        
        webview.uiDelegate = self //WKUIDelegate
        webview.navigationDelegate = self //WKNavigationDelegate
        webview.scrollView.bounces = false
        webview.scrollView.isScrollEnabled = true
        webview.allowsBackForwardNavigationGestures = true //webview 앞으로가기, 뒤로가기 제스처 사용 가능
        webview.allowsLinkPreview = false
        webview.isOpaque = false
        webview.backgroundColor = .white
        webview.contentMode = .scaleToFill
    }
    
    private func initActivityIndicatorView(){
        
        ActivityIndicatorView = UIActivityIndicatorView()
        ActivityIndicatorView.style = UIActivityIndicatorView.Style.gray
        ActivityIndicatorView.hidesWhenStopped = true //animate 중지시 화면에서 숨김
        self.webview.addSubview(ActivityIndicatorView)
        
        ActivityIndicatorView.translatesAutoresizingMaskIntoConstraints = false
        ActivityIndicatorView.centerXAnchor.constraint(equalTo: webview.centerXAnchor).isActive = true
        ActivityIndicatorView.centerYAnchor.constraint(equalTo: webview.centerYAnchor).isActive = true
    }
    
    private func initRefresh(){
        
        let RefreshControl = UIRefreshControl()
        RefreshControl.addTarget(self, action: #selector(updateRefresh(RefreshControl:)), for: .valueChanged)
        
        webview.scrollView.addSubview(RefreshControl)
        webview.scrollView.bounces = true
    }
    
    @objc func updateRefresh(RefreshControl : UIRefreshControl){
        
        initData()
        
        RefreshControl.endRefreshing()
    }
    
    private func initData(){
        
        let url = URL(string: "http://lotto.co.kr")!
        var request = URLRequest(url: url)
        webview.load(request)
    }
}

extension tab4view : WKNavigationDelegate {
    
    func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
        //print("시작")
        
        ActivityIndicatorView.startAnimating()
    }
    
    func webView(_ webView: WKWebView, didReceiveServerRedirectForProvisionalNavigation navigation: WKNavigation!) {
        //print("WKNavigationDelegate didReceiveServerRedirectForProvisionalNavigation")
    }
    
    func webView(_ webView: WKWebView, didCommit navigation: WKNavigation!) {
        //print("WKNavigationDelegate didCommit")
    }
    
    func webView(_ webView: WKWebView, didFailProvisionalNavigation navigation: WKNavigation!, withError error: Error) {
        //print("웹페이지 로드 실패")
        
        ActivityIndicatorView.stopAnimating()
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        //print("웹페이지 로드 완료")
        
        ActivityIndicatorView.stopAnimating()
    }
}

extension tab4view : WKUIDelegate {
    
    func webView(_ webView: WKWebView, runJavaScriptAlertPanelWithMessage message: String, initiatedByFrame frame: WKFrameInfo, completionHandler: @escaping () -> Void) {
        
        //print("runJavaScriptAlertPanelWithMessage:\(message)")
      
        let alert = UIAlertController(title: "", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "확인", style: .default, handler: { (UIAlertAction) in
            completionHandler()
        }))
       
        DispatchQueue.main.async {
            self.present(alert, animated: true)
        }
    }

    func webView(_ webView: WKWebView, runJavaScriptConfirmPanelWithMessage message: String, initiatedByFrame frame: WKFrameInfo, completionHandler: @escaping (Bool) -> Void) {
        
        //print("runJavaScriptConfirmPanelWithMessage:\(message)")
        
        let alert = UIAlertController(title: "", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "확인", style: .default, handler: { (UIAlertAction) in
            completionHandler(true)
        }))
       
        alert.addAction(UIAlertAction(title: "취소", style: .default, handler: { (UIAlertAction) in
            completionHandler(false)
        }))
        
        DispatchQueue.main.async {
            self.present(alert, animated: true)
        }
    }

    func webView(_ webView: WKWebView, runJavaScriptTextInputPanelWithPrompt prompt: String, defaultText: String?, initiatedByFrame frame: WKFrameInfo, completionHandler: @escaping (String?) -> Void) {
        
        //print("runJavaScriptTextInputPanelWithPrompt:\(prompt)")
    }
}
