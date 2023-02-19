import { useContext, useEffect } from "react"
import axios from "axios"
import './App.css'
import { CarContext } from "./CarProvider"

export default function Home() {
	const { newestCars, latestArrivals, setNewestCars, setLatestArrivals } = useContext(CarContext)

	const getNewestCars = async () => {
		const res = await axios.get('https://my-json-server.typicode.com/plyss/jdmmockjson/cars?_sort=year&_order=desc')
		setNewestCars(res.data)
	}

	const getLatestArrivals = async () => {
		const res = await axios.get('https://my-json-server.typicode.com/plyss/jdmmockjson/cars?_sort=id&_order=desc')
		setLatestArrivals(res.data)
	}

	useEffect(() => {
		getNewestCars()
		getLatestArrivals()
	}, [])

	const ImageCard = ({ car }) => (
		<div key={car.id} className="image-card-home">
			<img src={car.image} alt={`${car.make} ${car.model}`} className="image-home" />
			<p>{car.make} {car.model}</p>
		</div>
	)

	const ImageContainer = ({ cars }) => (
		<div className="image-container-home">
			{cars.map((car) => (
				<ImageCard key={car.id} car={car} />
			))}
		</div>
	)

	return <>
		<div>
			<div className="container-car">
				<h2>
					Newest Cars
				</h2>
				<ImageContainer cars={newestCars} />
			</div>
			<div className="container-car">
				<h2>
					Latest Arrivals
				</h2>
				<ImageContainer cars={latestArrivals} />
			</div>
		</div>
	</>
}
