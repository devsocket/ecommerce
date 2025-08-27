variable "vnet_name" {}
variable "location" {}
variable "resource_group_name" {}
variable "address_space" { 
    type = list(string)
    default = ["10.0.0.0/16"] 
}
variable "aks_subnet_name" {
  default = "aks-subnet"
}
variable "aks_subnet_prefix" {
  default = "10.0.1.0/24"
}
variable "db_subnet_name" {
  default = "db-subnet"
}
variable "db_subnet_prefix" {
  default = "10.0.2.0/24"
}
variable "tags" {
  type    = map(string)
  default = {}
}