class Droptable < ActiveRecord::Migration
  def up
  	drop_table :noticia
  end

  def down
  end
end
