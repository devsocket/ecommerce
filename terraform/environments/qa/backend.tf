terraform {
  backend "azurerm" {
    resource_group_name  = "ecommerce-qa-rg"
    storage_account_name = "qatfstateaccount"
    container_name       = "qatfstate"
    key                  = "qa.terraform.tfstate"
  }
}