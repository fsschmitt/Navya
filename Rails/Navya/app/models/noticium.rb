class Noticium < ActiveRecord::Base
  attr_accessible :category, :text, :title, :url
end
