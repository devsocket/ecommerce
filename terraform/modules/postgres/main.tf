resource "azurerm_postgresql_flexible_server" "this" {
  name                = var.postgres_name
  location            = var.location
  resource_group_name = var.resource_group_name
  administrator_login = "adminuser"
  administrator_password = var.admin_password
  sku_name           = "B_Standard_B1ms"
  version            = "13"
  storage_mb        = 32768
  backup_retention_days = 7
  geo_redundant_backup_enabled = false  
  lifecycle {
    ignore_changes = [
      zone
    ]
  }
}