import time
import requests
import threading
import numpy as np
import matplotlib.pyplot as plt
import argparse

# Function to send requests and measure elapsed time
def send_requests(url, qps, duration, results):
    interval = 1 / qps
    end_time = time.time() + duration
    elapsed_times = []

    while time.time() < end_time:
        start_time = time.time()
        try:
            response = requests.get(url)
            response.raise_for_status()
        except requests.exceptions.RequestException as e:
            print(f"Request failed: {e}")
        elapsed_times.append(time.time() - start_time)

        sleep_time = interval - (time.time() - start_time)
        if sleep_time > 0:
            time.sleep(sleep_time)

    results.extend(elapsed_times)

# Function to execute the experiment
def measure_performance(base_url, qps_values, duration):
    results = {}
    duration_per_qps = duration / len(qps_values)
    for qps in qps_values:
        print(f"Running test for QPS={qps}")
        threads = []
        thread_results = []
        for _ in range(5):
            thread_data = []
            thread_results.append(thread_data)
            thread = threading.Thread(target=send_requests, args=(base_url, qps, duration_per_qps, thread_data))
            threads.append(thread)

        for thread in threads:
            thread.start()

        for thread in threads:
            thread.join()

        # Flatten and calculate average elapsed time
        all_elapsed_times = [t for tr in thread_results for t in tr]
        avg_time = np.mean(all_elapsed_times)
        results[qps] = avg_time
    return results

# Main function
def main():
    parser = argparse.ArgumentParser(description="Measure API performance under varying QPS.")
    parser.add_argument("--url", type=str, required=True, help="The API endpoint to test.")
    parser.add_argument("--qps_from", type=int, required=True, help="Starting QPS value.")
    parser.add_argument("--qps_to", type=int, required=True, help="Ending QPS value.")
    parser.add_argument("--interval", type=int, required=True, help="Interval of QPS values.")
    parser.add_argument("--duration", type=int, required=True, help="Total duration for the test in seconds.")
    args = parser.parse_args()

    url = args.url
    qps_from = args.qps_from
    qps_to = args.qps_to
    interval = args.interval
    duration = args.duration

    qps_values = list(range(qps_from, qps_to + 1, interval))

    print(f"Measuring performance for URL: {url}")
    results = measure_performance(url, qps_values, duration)

    # Plot results
    plt.figure(figsize=(10, 6))
    plt.plot(qps_values, [results[qps] for qps in qps_values], label="Performance", marker='o')
    plt.title("Performance Analysis")
    plt.xlabel("QPS")
    plt.ylabel("Average Elapsed Time (s)")
    plt.legend()
    plt.grid()
    plt.show()

if __name__ == "__main__":
    main()
