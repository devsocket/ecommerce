variable "resource_group_name" {}
variable "location" {}
variable "cluster_name" {}
variable "key_vault_name" {}
variable "event_hub_namespace_name" {}
variable "postgres_name" {}
variable "db_admin_password" {}
variable "storage_account_name" {
  default = "qatfstateaccount"
}
variable "container_name" {
  default = "qatfstate" 
}
variable "tags" {
  type = map(string)
  default = {}
}