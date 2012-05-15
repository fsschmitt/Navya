class News < ActiveRecord::Base
  attr_accessible :category, :text, :title, :url
end
