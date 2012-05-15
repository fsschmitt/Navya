class CreateNews < ActiveRecord::Migration
  def change
    create_table :news do |t|
      t.string :url
      t.string :title
      t.text :text
      t.integer :category

      t.timestamps
    end
  end
end
