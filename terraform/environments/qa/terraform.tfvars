resource_group_name = "ecommerce-qa-rg"
location            = "canadacentral"
cluster_name        = "ecommerce-qa-aks"
key_vault_name = "ecommerce-qa-kv"
event_hub_namespace_name = "ecommerce-qa-eh"
postgres_name = "ecommerce-qa-db"
db_admin_password = "SuperSecretPassword@123"
storage_account_name = "qatfstateaccount"
container_name = "qatfstate"
tags = {
  environment = "qa"
}