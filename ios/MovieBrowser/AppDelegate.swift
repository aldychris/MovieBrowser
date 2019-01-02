import UIKit
import common

let KTUnit = KotlinUnit()

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    var api = MovieApiService(endPoint: "https://www.omdbapi.com", apiKey: YourApiKey.API_KEY)
    
    public lazy var apiService = {
        MovieBrowserRepositoryImpl(apiService: api)
    } ()
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        return true
    }
    
    static var me: AppDelegate {
        return UIApplication.shared.delegate as! AppDelegate
    }
    
    public func applicationWillTerminate(_ application: UIApplication) {
        // TODO: save context
    }


}

