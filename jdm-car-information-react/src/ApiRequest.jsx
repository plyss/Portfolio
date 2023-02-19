import axios from "axios"

export const getNewestCars = async (setNewestCars) => {
  const res = await axios.get('https://my-json-server.typicode.com/plyss/jdmmockjson/cars?_sort=year&_order=desc')
  setNewestCars(res.data)
}

export const getLatestArrivals = async (setLatestArrivals) => {
  const res = await axios.get('https://my-json-server.typicode.com/plyss/jdmmockjson/cars?_sort=id&_order=desc')
  setLatestArrivals(res.data)
}
