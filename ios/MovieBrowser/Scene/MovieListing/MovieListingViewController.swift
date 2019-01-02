import Foundation
import common
import UIKit
import Kingfisher

class MovieListingViewController: UIViewController,UITableViewDataSource,  UISearchBarDelegate, MovieListView {
    
    @IBOutlet var searchBar: UISearchBar!
    @IBOutlet var tableView: UITableView!
    @IBOutlet var actIndicator: UIActivityIndicatorView!
    
    private var data: [MovieViewModel] = []
    
    private let repository = AppDelegate.me.apiService
    private lazy var presenter: MovieListPresenter = {
        MovieListPresenter(uiContext: CoroutineDispatcher() as! KotlinCoroutineContext,
                           view: self,
                           repository: repository)
    }()
    
    override func viewDidLoad() {
        tableView.dataSource = self
        searchBar.delegate = self

        actIndicator.style = UIActivityIndicatorView.Style.whiteLarge
        showProgressIndicator(show: false)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        presenter.onDestroy()
    }
    
    func getMovieSuccsess(searchViewModel: SearchViewModel) {
        data = searchViewModel.movies
        tableView.reloadData()
    }
    
    func showProgressIndicator(show: Bool) {
        if(show){
            actIndicator.startAnimating()
        } else {
            actIndicator.stopAnimating()
        }
    }
    
    func showError(error: KotlinThrowable) {
        let alert = UIAlertController(title: "Alert", message: error.message, preferredStyle: UIAlertController.Style.alert)
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        presenter.getMovieList(title: searchBar.text!)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MovieCell", for: indexPath) as! MovieTabelViewCell
        
        let model = data[indexPath.row]
        
        cell.title?.text = model.title
        cell.year?.text = model.year
        cell.type?.text = model.type
        
        let processor = RoundCornerImageProcessor(cornerRadius: 25)
        cell.posterImg?.kf.setImage(with: URL(string: model.imgUrl), placeholder: UIImage(named: "placeholder.png"), options: [.processor(processor)])
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }

    
}
