//
//  MovieTabelViewCellTableViewCell.swift
//  MovieBrowser
//
//  Created by Aldy on 14/12/2018.
//  Copyright © 2018 Github. All rights reserved.
//

import UIKit

class MovieTabelViewCell: UITableViewCell {

    @IBOutlet var posterImg: UIImageView!
    @IBOutlet var title: UILabel!
    @IBOutlet var year: UILabel!
    @IBOutlet var type: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    


}
